package com.example.FilteringStudents.controller;

import com.example.FilteringStudents.dto.group.GroupPatch;
import com.example.FilteringStudents.dto.group.GroupPost;
import com.example.FilteringStudents.mapper.GroupMapper;
import com.example.FilteringStudents.service.GroupService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private GroupService groupService;
    private GroupMapper groupMapper;

    @Autowired
    public GroupController(GroupService groupService, GroupMapper groupMapper) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
    }

    @GetMapping("/all")
    public String getAllGroups(Model model) {
        model.addAttribute("groupGet", groupService.getAllGroups());


        return "/views/group/groupList";
    }

    @GetMapping("/{groupId}")
    public String getGroupById(@PathVariable Integer groupId, Model model) {
        model.addAttribute("groupGet", groupService.getGroupById(groupId));

        return "/views/group/groupByIndex";
    }

    @GetMapping("/new")
    public String newGroup(@ModelAttribute("groupPost") GroupPost groupPost) {
        return "/views/group/new";
    }

    @PostMapping
    public String createGroup(@ModelAttribute("groupPost") @Validated GroupPost groupPost, Model model) {
        groupService.createGroup(groupPost);

        return "redirect:/";

    }

    @GetMapping("/{groupId}/edit")
    public String editGroup(Model model, @PathVariable Integer groupId) {
        model.addAttribute("groupPatch", groupMapper.groupGetToGroupPatch(groupService.getGroupById(groupId)));

        return "/views/group/edit";
    }


    @PatchMapping("/{groupId}")
    public String updateGroup(@ModelAttribute("groupPatch") @Validated GroupPatch groupPatch, @PathVariable Integer groupId, Model model) {
        groupService.updateGroup(groupId, groupPatch);

        return "redirect:/";
    }

    @DeleteMapping("/{groupId}")
    public String deleteGroup(@PathVariable Integer groupId, Model model) {
        groupService.deleteGroup(groupId);

        return "redirect:/";
    }
}
