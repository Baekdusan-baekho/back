package com.example.demo.recipeapi.api;

import com.example.demo.recipeapi.dto.recipeListResponseDTO;
import com.example.demo.recipeapi.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/menu/recipe")
@CrossOrigin
public class RecipeController {

    private final RecipeService recipeService;


    // 전체 레시피 리스트 요청 처리
    @GetMapping("/total/{pageNum}")
    public ResponseEntity<?> getRecipeList(@PathVariable int pageNum){
        log.info("/api/recipe GET Recipe List Request");
        String responseDTO = recipeService.getRecipeList(pageNum);

        return ResponseEntity.ok().body(responseDTO);
    }

    // 카테고리별 레시피 리스트 요청 처리
    @GetMapping("/{category}/{pageNum}") // 예: 반찬/1페이지
    public ResponseEntity<?> getRecipeList(@PathVariable String category, @PathVariable int pageNum) {
        log.info("/api/menu/recipe/{}/{} - GET Recipe List by Category Request", category, pageNum);
        String responseDTO = recipeService.getRecipeListByCategory(category, pageNum);

        return ResponseEntity.ok().body(responseDTO);
    }

    // 레시피 상세보기 요청 처리
    @GetMapping("detail/{category}/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable String category, @PathVariable String id){
        try {
//            URLEncoder.encode(category, "UTF-8");
            log.info("/api/menu/recipe/detail/{}/{} - GET Recipe Detail Request"
                    , category, id);
            String responseDTO = recipeService.getRecipe(category, id);


            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }






}
