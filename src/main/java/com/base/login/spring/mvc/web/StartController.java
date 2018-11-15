package com.base.login.spring.mvc.web;

import com.base.login.spring.mvc.dao.GroupDao;
import com.base.login.spring.mvc.dao.model.Bookmark;
import com.base.login.spring.mvc.dao.model.Groups;
import com.base.login.spring.mvc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/test")
public class StartController {

    @Autowired
    private GroupDao groupDao;
    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(StartController.class);

    int idGroup;
    int idGroupForAdd;
    List<Bookmark> bookmarks;
    List<Bookmark> bookmarkSearch;
    String nameGroup;
    String name;
    String message;
    String messageForGroupName;
    int idUser;


    @GetMapping
    public String getAllGroups(Model model) {
        if (idUser == userService.getIdFromUser()) {
            model.addAttribute("listBookmarks", bookmarks);
        } else {
            groupDao.getGroupsForUser();
            model.addAttribute("listBookmarks", groupDao.allBookmarkForActiveUser());
        }
        model.addAttribute("userList", userService.userList());
        model.addAttribute("nameGroup", nameGroup);
        model.addAttribute("messageForGroupName", messageForGroupName);
        model.addAttribute("name", name);
        model.addAttribute("message", message);
        model.addAttribute("groupsForUser", groupDao.getGroupsForUser());
        model.addAttribute("bookmarkForSearch", bookmarkSearch);
        model.addAttribute("userRole", userService.getRoleByUser());
        bookmarkSearch = null;
        message = null;
        name = null;
        return "index_test";
    }

    @RequestMapping("/add")
    public String addGroup(@ModelAttribute("group") Groups group) {
        int count = 0;
        if (groupDao.checkNewGroupOnDublicate(group) == true) {
            message = "Уже есть группа с названием " + group.getNameGroup();
            count++;
        }
        if (group.getNameGroup() == null) {
            name = "group";
            count++;
        }
        if (count > 0) {
            return "redirect:/test";
        } else {
            groupDao.addGroup(group);
            logger.info("Add group = " + group.getNameGroup());
            return "redirect:/test";
        }
    }

    @RequestMapping("/remove/{id}")
    public String deleteGroup(@PathVariable("id") int id) {
        groupDao.deleteGroup(id);
        System.out.println(id);
        bookmarks = null;
        logger.info("Delete group with id = " + id);
        return "redirect:/test";
    }

    @RequestMapping("/removeBookmark/{id}")
    public String deleteBookmark(@PathVariable("id") int id) {
        groupDao.deleteBookmark(id);
        System.out.println(id);
        bookmarks = groupDao.getBookmarksfromOneGroup(idGroup);
        logger.info("Delete bookmark with id = " + id);
        return "redirect:/test";
    }

    @RequestMapping("/addInGroup/{id}")
    public String addBookmarkInGroup(@PathVariable("id") int id, Model model) {
        idGroupForAdd = id;
        model.addAttribute("nameGroup1", groupDao.getById(id).getNameGroup());
        logger.info("Add bookmark in group name = " + groupDao.getById(id).getNameGroup());
        return "addBookmark";
    }

    @RequestMapping("/addBookmark")
    public String addBookmark(@ModelAttribute("bookmark") Bookmark bookmark, Model model) {
        model.addAttribute("nameGroup1", groupDao.getById(idGroupForAdd).getNameGroup());
        System.out.println(bookmark);
        int count = 0;
        if (bookmark.getBookmark() == "") {
            model.addAttribute("name_bookmark", "name_bookmark");
            count++;
        }
        if (bookmark.getDescription() == "") {
            model.addAttribute("description", "description_bookmark");
            count++;
        }
        if (bookmark.getUrlBookmark() == "") {
            model.addAttribute("url", "url_bookmark");
            count++;
        }
        if (count > 0) {
            return "addBookmark";

        } else {
            groupDao.addBookmark(bookmark, idGroupForAdd);
            model.addAttribute("addBookmark", "Bookmark added to group " + "\"" + groupDao.getById(idGroupForAdd).getNameGroup() + "\"" + "!!!!");
            return "addBookmark";
        }
    }

    @RequestMapping("/getBookmarksFromOneGroup/{id}")
    public String getBookmarksFromOneGroup(@PathVariable("id") int id) {
        idUser = userService.getIdFromUser();
        idGroup = id;
        nameGroup = "\"" + groupDao.getById(id).getNameGroup() + "\"" + ":";
        messageForGroupName = "Print bookmarks group";
        bookmarks = groupDao.getBookmarksfromOneGroup(idGroup);
        return "redirect:/test";
    }

    @RequestMapping("/getHome")
    public String getHome() {
        return "redirect:/test";
    }

    @RequestMapping("/search")
    public String searchByName(@RequestParam String name, Model model) {
        bookmarkSearch = groupDao.getBookmarkForSearch(name);
        return "redirect:/test";
    }

    @RequestMapping("/showAllBookmarks")
    public String showAllBookmarks(Model model) {
        bookmarks = groupDao.allBookmarkForActiveUser();
        nameGroup = null;
        messageForGroupName = null;
        return "redirect:/test";
    }

}
