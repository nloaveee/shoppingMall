package com.shoppingMall.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileManagerService {
	
	// 실제 업로드가 된 이미지가 저장될 서버의 경로 
		// ***파일 마지막에 /를 꼭 붙이기***
		public static final String FILE_UPLOAD_PATH = "D:\\나현희\\shoppingMall\\workspace\\images/"; //학원
		//public static final String FILE_UPLOAD_PATH = "C:\\나현희\\shoppingMall\\workspace\\images/"; // 집
		
		// Q&A 파일
		// input: MultipartFile
		// output: String(이미지 경로)
		public String uploadFileInquiry(String name, MultipartFile file) {
		    // name 파라미터에 한글이 포함되어 있는지 확인
		    if (name != null && name.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
		        // 한글이 포함된 경우 영어로 변경 (UUID 사용)
		        name = UUID.randomUUID().toString();
		    }

		    // 폴더(디렉토리) 생성
		    // 예: aaaa_17348394850 또는 UUID_17348394850
		    String directoryName = name + "_" + System.currentTimeMillis();
		    
		    String filePath = FILE_UPLOAD_PATH + "inquiry/"  + directoryName + "/";

		    // 폴더 생성
		    File directory = new File(filePath);
		    if (!directory.mkdir()) {
		        // 폴더 생성시 실패하면 경로를 null로 리턴
		        return null;
		    }

		    // 파일 업로드
		    try {
		        byte[] bytes = file.getBytes();
		        // 파일 경로 설정
		        Path path = Paths.get(filePath + file.getOriginalFilename());
		        Files.write(path, bytes); // 실제 파일 업로드 진행
		    } catch (IOException e) {
		        e.printStackTrace();
		        return null; // 이미지 업로드 실패시 경로 null
		    }

		    // 파일업로드가 성공하면 이미지 url path를 리턴
		    return "/images/inquiry/" + directoryName + "/" + file.getOriginalFilename();
		}
		
		
		// item 파일
		public String uploadFileByItems(String name, MultipartFile file) {
		    // 폴더(디렉토리) 생성
		    // 예: aaaa_17348394850 또는 UUID_17348394850
		    String directoryName = name + "_" + System.currentTimeMillis();
		    String filePath = FILE_UPLOAD_PATH + "items/"  + directoryName + "/";

		    // 폴더 생성
		    File directory = new File(filePath);
		    if (!directory.mkdir()) {
		        // 폴더 생성시 실패하면 경로를 null로 리턴
		        return null;
		    }

		    // 파일 업로드
		    try {
		        byte[] bytes = file.getBytes();
		        // 파일 경로 설정
		        Path path = Paths.get(filePath + file.getOriginalFilename());
		        Files.write(path, bytes); // 실제 파일 업로드 진행
		    } catch (IOException e) {
		        e.printStackTrace();
		        return null; // 이미지 업로드 실패시 경로 null
		    }

		    // 파일업로드가 성공하면 이미지 url path를 리턴
		    return "/images/items/" + directoryName + "/" + file.getOriginalFilename();
		}
		
		
		// 파일 삭제 
		// input: 이미지 경로 (path)
		// output: x
		public void deleteFile(String imagePath) { 
			
			// 주소에 겹치는 /images/ 제거 
			Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
			
			// 삭제할 이미지가 존재하는가?
			if (Files.exists(path)) {
				// 이미지 삭제 
				try {
					Files.delete(path);
				} catch (IOException e) {
					log.info("[FileManagerService 파일삭제] 이미지 삭제 실패. path:{}", path.toString());
					return;
				}
				
				// 폴더(디렉토리) 삭제 
				path =path.getParent();
				if (Files.exists(path)) {
					try {
						Files.delete(path);
					} catch (IOException e) {
						log.info("[FileManagerService 파일삭제] 디렉토리 삭제 실패. path:{}", path.toString());
					}
				}
			}
		}

}
