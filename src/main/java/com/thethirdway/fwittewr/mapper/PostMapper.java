package com.thethirdway.fwittewr.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.thethirdway.fwittewr.dto.response.UserDTO;
import com.thethirdway.fwittewr.model.Post;

@Component
public class PostMapper {
	UserDTO.PostDTO toPostDTO(Post p) {
		UserDTO.PostDTO pDTO = new UserDTO.PostDTO();
		
		pDTO.setContent(p.getContent());
		pDTO.setPublishmentDate(p.getPublishmentDate());
		
		return pDTO; 
	}
	
	List<UserDTO.PostDTO> toPostDTOList(List<Post> list) {
		return list.stream().map(p->this.toPostDTO(p)).toList();
	}
}
