package com.example.FilteringStudents;

import com.example.FilteringStudents.dto.student.StudentGet;
import com.example.FilteringStudents.dto.student.StudentPatch;
import com.example.FilteringStudents.dto.student.StudentPost;
import com.example.FilteringStudents.enums.Degree;
import com.example.FilteringStudents.enums.FundingType;
import com.example.FilteringStudents.enums.Gender;
import com.example.FilteringStudents.enums.Status;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class StudentTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllStudents() throws Exception {
        List<StudentGet> studentGetList = new ArrayList<>();
        StudentGet studentGet1 = new StudentGet();
        StudentGet studentGet2 = new StudentGet();
        StudentGet studentGet3 = new StudentGet();
        StudentGet studentGet4 = new StudentGet();
        StudentGet studentGet5 = new StudentGet();
        ObjectMapper objectMapper = new ObjectMapper();

        studentGet1.setId(3);
        studentGet1.setGroupName("8В71");
        studentGet1.setFundingType("На основе бюджетного финансирования");
        studentGet1.setFullName("Железный Человек Рахманович");
        studentGet1.setGender("Мужской");
        studentGet1.setStatus("Учащийся");
        studentGet1.setCourse(3);
        studentGet1.setDegree("Бакалавр");

        studentGet2.setId(4);
        studentGet2.setGroupName("8В61");
        studentGet2.setFundingType("На договорной основе");
        studentGet2.setFullName("Крюгер Иван Михайлович");
        studentGet2.setGender("Мужской");
        studentGet2.setStatus("Учащийся");
        studentGet2.setCourse(4);
        studentGet2.setDegree("Бакалавр");

        studentGet3.setId(1);
        studentGet3.setGroupName("8В91");
        studentGet3.setFundingType("На основе бюджетного финансирования");
        studentGet3.setFullName("Романов Максим Юрьевич");
        studentGet3.setGender("Мужской");
        studentGet3.setStatus("Учащийся");
        studentGet3.setCourse(1);
        studentGet3.setDegree("Бакалавр");

        studentGet4.setId(5);
        studentGet4.setGroupName("8ВМ91");
        studentGet4.setFundingType("На договорной основе");
        studentGet4.setFullName("Романова Вдова Игоревна");
        studentGet4.setGender("Женский");
        studentGet4.setStatus("Учащийся");
        studentGet4.setCourse(1);
        studentGet4.setDegree("Магистр");

        studentGet5.setId(2);
        studentGet5.setGroupName("8В81");
        studentGet5.setFundingType("На основе бюджетного финансирования");
        studentGet5.setFullName("Человек Паук Бетменович");
        studentGet5.setGender("Мужской");
        studentGet5.setStatus("Учащийся");
        studentGet5.setCourse(2);
        studentGet5.setDegree("Бакалавр");

        studentGetList.add(studentGet1);
        studentGetList.add(studentGet2);
        studentGetList.add(studentGet3);
        studentGetList.add(studentGet4);
        studentGetList.add(studentGet5);

        studentGetList.sort(Comparator.comparing(StudentGet::getGroupName));
        studentGetList.sort(Comparator.comparing(StudentGet::getCourse));

        MvcResult mvcResult = this.mockMvc.perform(get("/students/all")).andExpect(status().isOk()).andReturn();
        Object objectStudentGet = mvcResult.getModelAndView().getModel().get("studentGet");

        String actual = objectMapper.writeValueAsString(objectStudentGet);
        String expected = objectMapper.writeValueAsString(studentGetList);

        assertEquals(expected, actual);
    }

    @Test
    public void getAllStudentsByGroupName() throws Exception {
        List<StudentGet> studentGetList = new ArrayList<>();
        StudentGet studentGet1 = new StudentGet();
        ObjectMapper objectMapper = new ObjectMapper();

        studentGet1.setId(3);
        studentGet1.setGroupName("8В71");
        studentGet1.setFundingType("На основе бюджетного финансирования");
        studentGet1.setFullName("Железный Человек Рахманович");
        studentGet1.setGender("Мужской");
        studentGet1.setStatus("Учащийся");
        studentGet1.setCourse(3);
        studentGet1.setDegree("Бакалавр");

        studentGetList.add(studentGet1);

        MvcResult mvcResult = this.mockMvc.perform(get("/students/all?groupName=8В71")).andExpect(status().isOk()).andReturn();
        Object objectStudentGet = mvcResult.getModelAndView().getModel().get("studentGet");

        String actual = objectMapper.writeValueAsString(objectStudentGet);
        String expected = objectMapper.writeValueAsString(studentGetList);

        assertEquals(expected, actual);
    }

    @Test
    public void createStudent() throws Exception {
        StudentPost studentPost = new StudentPost();
        StudentGet studentGet = new StudentGet();
        ObjectMapper objectMapper = new ObjectMapper();

        studentPost.setGroupName("8В71");
        studentPost.setFundingType(FundingType.BUDGET);
        studentPost.setSecondName("Иванов");
        studentPost.setName("Иван");
        studentPost.setSurname("Иванович");
        studentPost.setGender(Gender.MALE);
        studentPost.setStatus(Status.STUDENT);

        studentGet.setId(6);
        studentGet.setGroupName("8В71");
        studentGet.setFundingType("На основе бюджетного финансирования");
        studentGet.setFullName("Иванов Иван Иванович");
        studentGet.setGender("Мужской");
        studentGet.setStatus("Учащийся");
        studentGet.setCourse(3);
        studentGet.setDegree("Бакалавр");

        //Post
        var requestBuilderPost = post("/students").flashAttr("studentPost", studentPost).contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilderPost).andExpect(status().is3xxRedirection());

        //Get
        MvcResult mvcResult = this.mockMvc.perform(get("/students/6")).andExpect(status().isOk()).andReturn();
        Object objectStudentGet = mvcResult.getModelAndView().getModel().get("studentGet");

        String actual = objectMapper.writeValueAsString(objectStudentGet);
        String expected = objectMapper.writeValueAsString(studentGet);

        assertEquals(expected, actual);
    }

    @Test
    public void updateStudent() throws Exception {
        StudentPatch studentPatch = new StudentPatch();
        StudentGet studentGet = new StudentGet();
        ObjectMapper objectMapper = new ObjectMapper();

        studentPatch.setGroupName("8ВМ91");
        studentPatch.setFundingType(FundingType.CONTRACTUAL);
        studentPatch.setSecondName("Романова");
        studentPatch.setName("Вдова");
        studentPatch.setSurname("Игоревна");
        studentPatch.setGender(Gender.FEMALE);
        studentPatch.setStatus(Status.STUDENT);

        studentGet.setId(5);
        studentGet.setGroupName("8ВМ91");
        studentGet.setFundingType("На договорной основе");
        studentGet.setFullName("Романова Вдова Игоревна");
        studentGet.setGender("Женский");
        studentGet.setStatus("Учащийся");
        studentGet.setCourse(1);
        studentGet.setDegree("Магистр");

        //Patch
        var requestBuilderPatch = patch("/students/5").flashAttr("studentPatch", studentPatch).contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilderPatch).andExpect(status().is3xxRedirection());

        //Get
        MvcResult mvcResult = this.mockMvc.perform(get("/students/5")).andExpect(status().isOk()).andReturn();
        Object objectStudentGet = mvcResult.getModelAndView().getModel().get("studentGet");

        String actual = objectMapper.writeValueAsString(objectStudentGet);
        String expected = objectMapper.writeValueAsString(studentGet);

        assertEquals(expected, actual);
    }

    @Test
    public void deleteStudent() throws Exception {
        List<StudentGet> studentGetList = new ArrayList<>();
        StudentGet studentGet1 = new StudentGet();
        StudentGet studentGet2 = new StudentGet();
        StudentGet studentGet3 = new StudentGet();
        StudentGet studentGet4 = new StudentGet();
        ObjectMapper objectMapper = new ObjectMapper();

        studentGet1.setId(3);
        studentGet1.setGroupName("8В71");
        studentGet1.setFundingType("На основе бюджетного финансирования");
        studentGet1.setFullName("Железный Человек Рахманович");
        studentGet1.setGender("Мужской");
        studentGet1.setStatus("Учащийся");
        studentGet1.setCourse(3);
        studentGet1.setDegree("Бакалавр");

        studentGet2.setId(4);
        studentGet2.setGroupName("8В61");
        studentGet2.setFundingType("На договорной основе");
        studentGet2.setFullName("Крюгер Иван Михайлович");
        studentGet2.setGender("Мужской");
        studentGet2.setStatus("Учащийся");
        studentGet2.setCourse(4);
        studentGet2.setDegree("Бакалавр");

        studentGet3.setId(1);
        studentGet3.setGroupName("8В91");
        studentGet3.setFundingType("На основе бюджетного финансирования");
        studentGet3.setFullName("Романов Максим Юрьевич");
        studentGet3.setGender("Мужской");
        studentGet3.setStatus("Учащийся");
        studentGet3.setCourse(1);
        studentGet3.setDegree("Бакалавр");

        studentGet4.setId(2);
        studentGet4.setGroupName("8В81");
        studentGet4.setFundingType("На основе бюджетного финансирования");
        studentGet4.setFullName("Человек Паук Бетменович");
        studentGet4.setGender("Мужской");
        studentGet4.setStatus("Учащийся");
        studentGet4.setCourse(2);
        studentGet4.setDegree("Бакалавр");

        studentGetList.add(studentGet1);
        studentGetList.add(studentGet2);
        studentGetList.add(studentGet3);
        studentGetList.add(studentGet4);

        studentGetList.sort(Comparator.comparing(StudentGet::getGroupName));
        studentGetList.sort(Comparator.comparing(StudentGet::getCourse));

        //Delete
        this.mockMvc.perform(delete("/students/5")).andExpect(status().is3xxRedirection());

        //Get
        MvcResult mvcResult = this.mockMvc.perform(get("/students/all")).andExpect(status().isOk()).andReturn();
        Object objectStudentGet = mvcResult.getModelAndView().getModel().get("studentGet");

        String actual = objectMapper.writeValueAsString(objectStudentGet);
        String expected = objectMapper.writeValueAsString(studentGetList);

        assertEquals(expected, actual);
    }

    @Test
    public void getAllStudentsByGroupNameWithError() throws Exception {
        //Delete
        this.mockMvc.perform(delete("/students/1")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/students/2")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/students/3")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/students/4")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/students/5")).andExpect(status().is3xxRedirection());

        //Get
        MvcResult mvcResult = this.mockMvc.perform(get("/students/all?groupName=8В71")).andExpect(status().isOk()).andReturn();
        Object objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        String actual = objectErrorMessage.toString();
        String expected = "В группе 8В71 нет студентов";

        assertEquals(expected, actual);

        //Get
        mvcResult = this.mockMvc.perform(get("/students/all?groupName=8А71")).andExpect(status().isOk()).andReturn();
        objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        actual = objectErrorMessage.toString();
        expected = "Группы 8А71 не существует";

        assertEquals(expected, actual);

        //Get
        mvcResult = this.mockMvc.perform(get("/students/1")).andExpect(status().isOk()).andReturn();
        objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        actual = objectErrorMessage.toString();
        expected = "Студента с id: 1 не существует";

        assertEquals(expected, actual);
    }

    @Test
    public void getAllStudentWithError() throws Exception {
        //Delete
        this.mockMvc.perform(delete("/students/1")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/students/2")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/students/3")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/students/4")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/students/5")).andExpect(status().is3xxRedirection());

        //Get
        MvcResult mvcResult = this.mockMvc.perform(get("/students/all")).andExpect(status().isOk()).andReturn();
        Object objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        String actual = objectErrorMessage.toString();
        String expected = "Список студентов пуст";

        assertEquals(expected, actual);
    }

    @Test
    public void createStudentWithError() throws Exception {
        StudentPost studentPost = new StudentPost();

        studentPost.setGroupName("8В71");
        studentPost.setFundingType(FundingType.BUDGET);
        studentPost.setSecondName("Иванов");
        studentPost.setName("Иван");
        studentPost.setSurname("Иванович");
        studentPost.setGender(Gender.MALE);
        studentPost.setStatus(Status.STUDENT);

        //Delete
        this.mockMvc.perform(delete("/students/1")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/students/2")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/students/3")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/students/4")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/students/5")).andExpect(status().is3xxRedirection());

        //Get
        MvcResult mvcResult = this.mockMvc.perform(get("/students/all")).andExpect(status().isOk()).andReturn();
        Object objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        String actual = objectErrorMessage.toString();
        String expected = "Список студентов пуст";

        assertEquals(expected, actual);

        //Delete
        this.mockMvc.perform(delete("/groups/1")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/groups/2")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/groups/3")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/groups/4")).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(delete("/groups/5")).andExpect(status().is3xxRedirection());

        //Post
        var requestBuilderPost = post("/students").flashAttr("studentPost", studentPost).contentType(MediaType.APPLICATION_JSON);

        mvcResult = this.mockMvc.perform(requestBuilderPost).andExpect(status().isOk()).andReturn();
        objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        actual = objectErrorMessage.toString();
        expected = "Сначала нужно создать группу";

        assertEquals(expected, actual);
    }

    @Test
    public void updateStudentWithError() throws Exception {
        StudentPatch studentPatch = new StudentPatch();

        studentPatch.setGroupName("8ВМ91");
        studentPatch.setFundingType(FundingType.CONTRACTUAL);
        studentPatch.setSecondName("Романова");
        studentPatch.setName("Вдова");
        studentPatch.setSurname("Игоревна");
        studentPatch.setGender(Gender.FEMALE);
        studentPatch.setStatus(Status.STUDENT);

        //Patch
        var requestBuilderPatch = patch("/students/6").flashAttr("studentPatch", studentPatch).contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(requestBuilderPatch).andExpect(status().isOk()).andReturn();
        Object objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        String actual = objectErrorMessage.toString();
        String expected = "Студента с id: 6 не существует";

        assertEquals(expected, actual);

        studentPatch.setGroupName("8ТМ91");

        //Patch
        requestBuilderPatch = patch("/students/5").flashAttr("studentPatch", studentPatch).contentType(MediaType.APPLICATION_JSON);

        mvcResult = this.mockMvc.perform(requestBuilderPatch).andExpect(status().isOk()).andReturn();
        objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        actual = objectErrorMessage.toString();
        expected = "Группы: 8ТМ91 не существует";

        assertEquals(expected, actual);
    }

    @Test
    public void deleteStudentWithError() throws Exception {
        //Delete
        MvcResult mvcResult = this.mockMvc.perform(delete("/students/6")).andExpect(status().isOk()).andReturn();
        Object objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        String actual = objectErrorMessage.toString();
        String expected = "Студента с id: 6 не существует";

        assertEquals(expected, actual);
    }
}
