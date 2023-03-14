package com.mrthinkj.kythucac.service.file;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.modelDTO.book.ChapterPurchaseDTO;
import com.mrthinkj.kythucac.repository.book.ChapterPurchaseRepository;
import com.mrthinkj.kythucac.service.convert.Convert;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class Excel {
    @Autowired
    ChapterPurchaseRepository chapterPurchaseRepository;
    @Autowired
    Convert convert;
    public Workbook chapterPurchaseData(String bookName){
        Book book = convert.findBookByName(bookName);
        Workbook workbook = new XSSFWorkbook();
        DataFormatter formatter = new DataFormatter();
        List<ChapterPurchaseDTO> data = chapterPurchaseRepository.findAllChapterPurchaseConvert(book.getId());
        Sheet sheet = workbook.createSheet("Lịch sử mua chương của truyện "+book.getName());
        int rowIndex = 0;
        int colIndex = 0;
        Row row = sheet.createRow(rowIndex++);
        Cell cell = row.createCell(colIndex++);
        cell.setCellValue((String) "Tên chương");
        cell = row.createCell(colIndex++);
        cell.setCellValue((String) "Ngày mua");
        cell = row.createCell(colIndex++);
        cell.setCellValue((String) "Giá");
        cell = row.createCell(colIndex++);
        cell.setCellValue((String) "Tên người mua");
        cell = row.createCell(colIndex);
        cell.setCellValue((String) "Id người mua");
        for (ChapterPurchaseDTO rowData : data){
            Date date = rowData.getPurchaseDate();
            row = sheet.createRow(rowIndex++);
            colIndex = 0;
            cell = row.createCell(colIndex++);
            cell.setCellValue((String) rowData.getChapterName());
            // Date
            cell = row.createCell(colIndex++);
            CellStyle dateStyle = row.getSheet().getWorkbook().createCellStyle();
            CreationHelper createHelper = row.getSheet().getWorkbook().getCreationHelper();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
            cell.setCellValue((Date) rowData.getPurchaseDate());
            cell.setCellStyle(dateStyle);
            // Continue
            cell = row.createCell(colIndex++);
            cell.setCellValue((Integer) rowData.getChapterPrice());
            cell = row.createCell(colIndex++);
            cell.setCellValue((String) rowData.getUsername());
            cell = row.createCell(colIndex);
            cell.setCellValue((Integer) rowData.getUserId());
        }
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }
        return workbook;
    }
}
