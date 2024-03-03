package zerobase.weather.controller;
/*
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;*/
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.error.InvaildDate;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "DiaryController", description = "Diary-Controller")
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

   // @ApiOperation(value = "일기 텍스트와 날씨를 이용하여 DB에 일기 저장", notes = "이것은 노트")
    @Operation(summary = "일기 텍스트와 날씨를 이용하여 DB에 일기 저장", description = "이것은 노트")
    @PostMapping("/create/diary")
     void createDiary(
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody String text
            ){
        diaryService.createDiary(date, text);
    }

   // @ApiOperation(value = "선택한 날짜의 모든 일기 데이터를 가져옵니다")
    @Operation(summary = "선택한 날짜의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diary")
    List<Diary> readDiary(
                @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                LocalDate date
        ){
        return diaryService.readDiary(date);
        }

   // @ApiOperation("선택한 기간 중의 모든 일기 데이터를 가져옵니다")
    @Operation(summary = "선택한 기간 중의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
            @RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
           // @ApiParam(value = "조회할 기간의 첫날", example = "2020-02-02") LocalDate startDate,
            @Parameter(name = "조회할 기간의 첫날", example = "2024-02-02") LocalDate startDate,
            @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
           // @ApiParam(value = "조회할 기간의 마지막날", example = "2020-02-02") LocalDate endDate
            @Parameter(name = "조회할 기간의 마지막날", example = "2024-09-02") LocalDate endDate
    ){
        return diaryService.readDiaries(startDate, endDate);
    }

    @PutMapping("/update/diary")
    void updateDiary(
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date,
            @RequestBody String text
    ){
        diaryService.updateDiary(date, text);
    }

    @DeleteMapping("/delete/diary")
    void deleteDiary(
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date
    ){
        diaryService.deleteDiary(date);
    }
}
