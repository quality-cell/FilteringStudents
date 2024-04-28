package com.example.FilteringStudents;

import com.example.FilteringStudents.dto.group.GroupGet;
import com.example.FilteringStudents.dto.group.GroupPatch;
import com.example.FilteringStudents.dto.group.GroupPost;
import com.example.FilteringStudents.enums.Degree;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class GroupTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllGroups() throws Exception {
        List<GroupGet> groupGetList = new ArrayList<>();
        GroupGet groupGet1 = new GroupGet();
        GroupGet groupGet2 = new GroupGet();
        GroupGet groupGet3 = new GroupGet();
        GroupGet groupGet4 = new GroupGet();
        GroupGet groupGet5 = new GroupGet();
        ObjectMapper objectMapper = new ObjectMapper();

        groupGet1.setId(1);
        groupGet1.setName("8В91");
        groupGet1.setCourse(1);
        groupGet1.setDegree("Бакалавр");

        groupGet2.setId(2);
        groupGet2.setName("8В81");
        groupGet2.setCourse(2);
        groupGet2.setDegree("Бакалавр");

        groupGet3.setId(3);
        groupGet3.setName("8В71");
        groupGet3.setCourse(3);
        groupGet3.setDegree("Бакалавр");

        groupGet4.setId(4);
        groupGet4.setName("8В61");
        groupGet4.setCourse(4);
        groupGet4.setDegree("Бакалавр");

        groupGet5.setId(5);
        groupGet5.setName("8ВМ91");
        groupGet5.setCourse(1);
        groupGet5.setDegree("Магистр");

        groupGetList.add(groupGet1);
        groupGetList.add(groupGet2);
        groupGetList.add(groupGet3);
        groupGetList.add(groupGet4);
        groupGetList.add(groupGet5);

        MvcResult mvcResult = this.mockMvc.perform(get("/groups/all")).andExpect(status().isOk()).andReturn();
        Object objectGroupGet = mvcResult.getModelAndView().getModel().get("groupGet");

        String actual = objectMapper.writeValueAsString(objectGroupGet);
        String expected = objectMapper.writeValueAsString(groupGetList);

        assertEquals(expected, actual);
    }

    @Test
    public void getGroupById() throws Exception {
        GroupGet groupGet = new GroupGet();
        ObjectMapper objectMapper = new ObjectMapper();

        groupGet.setId(1);
        groupGet.setName("8В91");
        groupGet.setCourse(1);
        groupGet.setDegree("Бакалавр");

        MvcResult mvcResult = this.mockMvc.perform(get("/groups/1")).andExpect(status().isOk()).andReturn();
        Object objectGroupGet = mvcResult.getModelAndView().getModel().get("groupGet");

        String actual = objectMapper.writeValueAsString(objectGroupGet);
        String expected = objectMapper.writeValueAsString(groupGet);

        assertEquals(expected, actual);
    }

    @Test
    public void createGroup() throws Exception {
        GroupPost groupPost = new GroupPost();
        GroupGet groupGet = new GroupGet();
        ObjectMapper objectMapper = new ObjectMapper();

        groupPost.setName("8В92");
        groupPost.setCourse(1);
        groupPost.setDegree(Degree.BACHELOR);

        groupGet.setId(7);
        groupGet.setName("8В92");
        groupGet.setCourse(1);
        groupGet.setDegree("Бакалавр");

        //Post
        var requestBuilderPost = post("/groups").flashAttr("groupPost", groupPost).contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilderPost).andExpect(status().is3xxRedirection());

        //Get
        MvcResult mvcResult = this.mockMvc.perform(get("/groups/7")).andExpect(status().isOk()).andReturn();
        Object objectGroupGet = mvcResult.getModelAndView().getModel().get("groupGet");

        String actual = objectMapper.writeValueAsString(objectGroupGet);
        String expected = objectMapper.writeValueAsString(groupGet);

        assertEquals(expected, actual);
    }

    @Test
    public void updateGroup() throws Exception {
        GroupPatch groupPatch = new GroupPatch();
        GroupGet groupGet = new GroupGet();
        ObjectMapper objectMapper = new ObjectMapper();

        groupPatch.setName("8В91");
        groupPatch.setCourse(3);
        groupPatch.setDegree(Degree.BACHELOR);

        groupGet.setId(1);
        groupGet.setName("8В91");
        groupGet.setCourse(3);
        groupGet.setDegree("Бакалавр");

        //Patch
        var requestBuilderPatch = patch("/groups/1").flashAttr("groupPatch", groupPatch).contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilderPatch).andExpect(status().is3xxRedirection());

        //Get
        MvcResult mvcResult = this.mockMvc.perform(get("/groups/1")).andExpect(status().isOk()).andReturn();
        Object objectGroupGet = mvcResult.getModelAndView().getModel().get("groupGet");

        String actual = objectMapper.writeValueAsString(objectGroupGet);
        String expected = objectMapper.writeValueAsString(groupGet);

        assertEquals(expected, actual);
    }

    @Test
    public void deleteGroup() throws Exception {
        List<GroupGet> groupGetList = new ArrayList<>();
        GroupGet groupGet1 = new GroupGet();
        GroupGet groupGet2 = new GroupGet();
        GroupGet groupGet3 = new GroupGet();
        GroupGet groupGet4 = new GroupGet();
        GroupGet groupGet5 = new GroupGet();
        GroupGet groupGet6 = new GroupGet();
        GroupPost groupPost = new GroupPost();
        ObjectMapper objectMapper = new ObjectMapper();

        groupGet1.setId(1);
        groupGet1.setName("8В91");
        groupGet1.setCourse(1);
        groupGet1.setDegree("Бакалавр");

        groupGet2.setId(2);
        groupGet2.setName("8В81");
        groupGet2.setCourse(2);
        groupGet2.setDegree("Бакалавр");

        groupGet3.setId(3);
        groupGet3.setName("8В71");
        groupGet3.setCourse(3);
        groupGet3.setDegree("Бакалавр");

        groupGet4.setId(4);
        groupGet4.setName("8В61");
        groupGet4.setCourse(4);
        groupGet4.setDegree("Бакалавр");

        groupGet5.setId(5);
        groupGet5.setName("8ВМ91");
        groupGet5.setCourse(1);
        groupGet5.setDegree("Магистр");

        groupGet6.setId(6);
        groupGet6.setName("8В92");
        groupGet6.setCourse(1);
        groupGet6.setDegree("Бакалавр");

        groupPost.setName("8В92");
        groupPost.setCourse(1);
        groupPost.setDegree(Degree.BACHELOR);

        groupGetList.add(groupGet1);
        groupGetList.add(groupGet2);
        groupGetList.add(groupGet3);
        groupGetList.add(groupGet4);
        groupGetList.add(groupGet5);
        groupGetList.add(groupGet6);

        //Post
        var requestBuilderPost = post("/groups").flashAttr("groupPost", groupPost).contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilderPost).andExpect(status().is3xxRedirection());

        //Get
        MvcResult mvcResult = this.mockMvc.perform(get("/groups/all")).andExpect(status().isOk()).andReturn();
        Object objectGroupGet = mvcResult.getModelAndView().getModel().get("groupGet");

        String actual = objectMapper.writeValueAsString(objectGroupGet);
        String expected = objectMapper.writeValueAsString(groupGetList);

        assertEquals(expected, actual);

        //Delete
        this.mockMvc.perform(delete("/groups/6")).andExpect(status().is3xxRedirection());
        groupGetList.remove(5);

        //Get
        mvcResult = this.mockMvc.perform(get("/groups/all")).andExpect(status().isOk()).andReturn();
        objectGroupGet = mvcResult.getModelAndView().getModel().get("groupGet");

        actual = objectMapper.writeValueAsString(objectGroupGet);
        expected = objectMapper.writeValueAsString(groupGetList);

        assertEquals(expected, actual);
    }

    @Test
    public void getAllGroupsWithError() throws Exception {
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

        //Get
        mvcResult = this.mockMvc.perform(get("/groups/all")).andExpect(status().isOk()).andReturn();
        objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        actual = objectErrorMessage.toString();
        expected = "Список групп пуст";

        assertEquals(expected, actual);
    }

    @Test
    public void getGroupByIdWithError() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/groups/6")).andExpect(status().isOk()).andReturn();
        Object objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        String actual = objectErrorMessage.toString();
        String expected = "Группа с id: 6 не существует";

        assertEquals(expected, actual);
    }

    @Test
    public void createGroupWithError() throws Exception {
        GroupPost groupPost = new GroupPost();

        groupPost.setName("8В91");
        groupPost.setCourse(1);
        groupPost.setDegree(Degree.BACHELOR);

        //Post
        var requestBuilderPost = post("/groups").flashAttr("groupPost", groupPost).contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(requestBuilderPost).andExpect(status().isOk()).andReturn();
        Object objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        String actual = objectErrorMessage.toString();
        String expected = "Группа 8В91 уже существует";

        assertEquals(expected, actual);

        groupPost.setName("8В92");
        groupPost.setCourse(5);

        //Post
        requestBuilderPost = post("/groups").flashAttr("groupPost", groupPost).contentType(MediaType.APPLICATION_JSON);

        mvcResult = this.mockMvc.perform(requestBuilderPost).andExpect(status().isOk()).andReturn();
        objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        actual = objectErrorMessage.toString();
        expected = "Степень не соответствует курсу обучения";

        assertEquals(expected, actual);
    }

    @Test
    public void updateGroupWithError() throws Exception {
        GroupPatch groupPatch = new GroupPatch();

        groupPatch.setId(2);
        groupPatch.setName("8В92");
        groupPatch.setCourse(3);
        groupPatch.setDegree(Degree.BACHELOR);

        //Patch
        var requestBuilderPatch = patch("/groups/6").flashAttr("groupPatch", groupPatch).contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(requestBuilderPatch).andExpect(status().isOk()).andReturn();
        Object objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        String actual = objectErrorMessage.toString();
        String expected = "Группа с id: 6 не существует";

        assertEquals(expected, actual);

        groupPatch.setCourse(5);

        //Patch
        requestBuilderPatch = patch("/groups/2").flashAttr("groupPatch", groupPatch).contentType(MediaType.APPLICATION_JSON);

        mvcResult = this.mockMvc.perform(requestBuilderPatch).andExpect(status().isOk()).andReturn();
        objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        actual = objectErrorMessage.toString();
        expected = "Степень не соответствует курсу обучения";

        assertEquals(expected, actual);

        groupPatch.setName("8В91");

        //Patch
        requestBuilderPatch = patch("/groups/2").flashAttr("groupPatch", groupPatch).contentType(MediaType.APPLICATION_JSON);

        mvcResult = this.mockMvc.perform(requestBuilderPatch).andExpect(status().isOk()).andReturn();
        objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        actual = objectErrorMessage.toString();
        expected = "Группа 8В91 уже существует";

        assertEquals(expected, actual);
    }

    @Test
    public void deleteGroupWithError() throws Exception {
        //Delete
        MvcResult mvcResult = this.mockMvc.perform(delete("/groups/6")).andExpect(status().isOk()).andReturn();
        Object objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        String actual = objectErrorMessage.toString();
        String expected = "Группа с id: 6 не существует";

        assertEquals(expected, actual);

        //Delete
        mvcResult = this.mockMvc.perform(delete("/groups/1")).andExpect(status().isOk()).andReturn();
        objectErrorMessage = mvcResult.getModelAndView().getModel().get("errorMessage");

        actual = objectErrorMessage.toString();
        expected = "В группе с id: 1 еще есть студенты";

        assertEquals(expected, actual);
    }
}
