package com.egswebapp.egsweb.controller;

import com.egswebapp.egsweb.dto.request.MenuRequestDto;
import com.egswebapp.egsweb.dto.response.ApiResponse;
import com.egswebapp.egsweb.dto.response.MenuResponse;
import com.egswebapp.egsweb.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "MenuController Resource, functionality for user ", description = "shows menu functionality")
@Slf4j
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * Get all Menu
     */


    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Get all menu ")
    public ResponseEntity<?> getAllMenu() {
        log.info("Trying to get all menu");
        List<MenuResponse> menuResponseList = menuService.getAllMenu();
        return new ResponseEntity<>(menuResponseList, HttpStatus.OK);
    }

    /**
     * Add Menu
     *
     * @param menuRequestDto the {@Link menuRequestDto}
     */


    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "Add Menu ")
    public ResponseEntity<ApiResponse> addMenu(@Valid @RequestBody MenuRequestDto menuRequestDto) {

        log.info("trying to add menu");
        menuService.addMenu(menuRequestDto);
        return new ResponseEntity<>(new ApiResponse(true, "menu is created"), HttpStatus.CREATED);
    }

    /**
     * update Menu
     *
     * @param menuRequestDto the {@Link menuRequestDto}
     * @param id             the {@Link id}
     */


    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "update Menu")
    public ResponseEntity<?> updateMenu(@Valid @RequestBody MenuRequestDto menuRequestDto, @PathVariable(name = "id") Long id) {

        log.info("trying to update menu");
        MenuResponse menuResponse = menuService.updateMenu(menuRequestDto, id);
        return new ResponseEntity<>(menuResponse, HttpStatus.OK);
    }

    /**
     * Get menu by  id
     *
     * @param id the {@Link id} id of menu
     */

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Get menu by  id")
    public ResponseEntity<?> getMenuById(@PathVariable(name = "id") Long id) {

        log.info("trying to get menu by id");
        MenuResponse menu = menuService.getMenuById(id);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }


    /**
     * Delete menu by  id
     *
     * @param id the {@Link id} id of menu
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Delete menu by id")
    public ResponseEntity<?> deleteMenu(@PathVariable(name = "id") Long id) {

        log.info("trying to delete menu");
        menuService.deleteMenu(id);
        return new ResponseEntity<>(new ApiResponse(true, "You successfully deleted menu"), HttpStatus.OK);
    }
}
