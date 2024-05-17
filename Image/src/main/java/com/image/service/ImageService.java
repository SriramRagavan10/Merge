package com.image.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.image.entity.Image;
import com.image.repository.ImageRepository;
import com.image.util.ImageUtils;

@Service
public class ImageService {

	@Autowired
	ImageRepository imageRepository;

	public String uploadImage(MultipartFile file) throws IOException {
		Image img = imageRepository.save(Image.builder().name(file.getOriginalFilename()).type(file.getContentType())
				.data(ImageUtils.compressImage(file.getBytes())).build());

		if (img != null) {
			return "File Uploaded Successfull : " + file.getOriginalFilename();
		}
		return null;
	}

	public byte[] dowload(Long id) {
		Optional<Image> dbImg = imageRepository.findById(id);
		byte[] imgs = ImageUtils.decompressImage(dbImg.get().getData());
		return imgs;
	}

}
