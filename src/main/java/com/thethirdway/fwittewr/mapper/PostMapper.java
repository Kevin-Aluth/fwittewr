package com.thethirdway.fwittewr.mapper;


import java.util.List;

import org.springframework.stereotype.Component;

import com.thethirdway.fwittewr.dto.request.CreatePostDTO;
import com.thethirdway.fwittewr.dto.response.PostDTO;
import com.thethirdway.fwittewr.dto.response.UserDTO;
import com.thethirdway.fwittewr.model.Post;
import com.thethirdway.fwittewr.model.User;

@Component
public class PostMapper {
	public Post fromCreatePostDTO(CreatePostDTO pDTO) {
		Post p = new Post();
		p.setContent(pDTO.getContent());
		
		return p;
	}
	
	public PostDTO toPostDTO(Post p) {
		PostDTO pDTO = new PostDTO(); 
		
		pDTO.setId(p.getId());
		pDTO.setPosterName(toInternalUserDTO(p.getUser()));
		pDTO.setContent(p.getContent());
		pDTO.setPublishmentDate(p.getPublishmentDate());
		pDTO.setLikedUsers(toInternalUserDTOList(p.getLikedUsers()));
		
		return pDTO; 
	}
	
	public List<PostDTO> toPostDTOList(List<Post> list) {
		return list.stream().map(p->this.toPostDTO(p)).toList();
	}
	
	public PostDTO.InternalUserDTO toInternalUserDTO(User u) {
		PostDTO.InternalUserDTO uDTO = new PostDTO.InternalUserDTO(); 
		uDTO.setId(u.getId());
		uDTO.setName(u.getName());
		return uDTO; 
	}
	
	public List<PostDTO.InternalUserDTO> toInternalUserDTOList(List<User> list) {
		return list.stream().map(u->this.toInternalUserDTO(u)).toList(); 
	}
}
