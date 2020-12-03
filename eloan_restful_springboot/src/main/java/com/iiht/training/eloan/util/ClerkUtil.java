package com.iiht.training.eloan.util;

import com.iiht.training.eloan.dto.ClerkDto;
import com.iiht.training.eloan.entity.Clerk;

public class ClerkUtil 
{
	public ClerkDto convertClerkEntityToOutputDto(Clerk clerk) 
	{
		ClerkDto clerkOutputDto = new ClerkDto();
		clerkOutputDto.setClerkId(clerk.getClerkId());
		clerkOutputDto.setName(clerk.getName());
		return clerkOutputDto;
	}
	
	
	public Clerk convertClerkInputDtoToEntity(ClerkDto clerkInputDTO) 
	{
		Clerk clerk = new Clerk();
		clerk.setClerkId(clerkInputDTO.getClerkId());
		clerk.setName(clerkInputDTO.getName());
		
		return clerk;
	}
	
	
}
