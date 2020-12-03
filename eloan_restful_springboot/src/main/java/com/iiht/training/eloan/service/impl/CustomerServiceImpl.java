package com.iiht.training.eloan.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.dto.ClerkDto;
import com.iiht.training.eloan.dto.LoanDto;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.dto.exception.ExceptionResponse;
import com.iiht.training.eloan.entity.Clerk;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.entity.Users;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private ProcessingInfoRepository processingInfoRepository;

	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;


	private UserDto convertUserEntityToOutputDto(Users user) 
	{
		UserDto userOutputDto = new UserDto();
		userOutputDto.setCustomerId(user.getCustomerId());
		userOutputDto.setFirstName(user.getFirstName());
		userOutputDto.setLastName(user.getLastName());
		userOutputDto.setEmail(user.getEmail());
		userOutputDto.setMobile(user.getMobile());
		userOutputDto.setAddress(user.getAddress());
		return userOutputDto;
	}


	private Users convertUserInputDtoToEntity(UserDto userInputDto) 
	{
		Users user = new Users();
		user.setCustomerId(userInputDto.getCustomerId());
		user.setFirstName(userInputDto.getFirstName());
		user.setLastName(userInputDto.getLastName());
		user.setEmail(userInputDto.getEmail());
		user.setMobile(userInputDto.getMobile());
		user.setAddress(userInputDto.getAddress());
		return user;
	}
	
	private LoanOutputDto convertLoanEntityToOutputDto(Loan loanApplied, LoanDto loanInputDto) 
	{
		LoanOutputDto loanOutputDto = new LoanOutputDto();
		loanOutputDto.setCustomerId(loanApplied.getCustomerId());
		loanOutputDto.setLoanAppId(loanApplied.getLoanId());
		loanOutputDto.setLoanDto(loanInputDto);
		loanOutputDto.setRemark(loanApplied.getRemark());
		loanOutputDto.setStatus(Integer.toString(loanApplied.getStatus()));
		loanOutputDto.setUserDto(convertUserEntityToOutputDto(this.usersRepository.getOne(loanApplied.getCustomerId())));
		return loanOutputDto;
	}
	
	private Loan convertLoanInputDtoToEntity(Long customerId, LoanDto loanInputDto) 
	{
		Loan loan = new Loan();
		loan.setLoanName(loanInputDto.getLoanName());
		loan.setLoanAmount(loanInputDto.getLoanAmount());
		loan.setCustomerId(customerId);
		loan.setLoanApplicationDate(loanInputDto.getLoanApplicationDate());
		loan.setBusinessStructure(loanInputDto.getBusinessStructure());
		loan.setBillingIndicator(loanInputDto.getBillingIndicator());
		loan.setTaxIndicator(loanInputDto.getTaxIndicator());
		loan.setRemark("request for loan");
		loan.setStatus(0);
		return loan;
	}

	
	@Override
	public UserDto register(UserDto userDto) {
		
		/*if(userDto!=null)
		{
			if(this.usersRepository.existsById(userDto.getUserId())) 
			{
				throw new ExceptionResponse("User ID "+userDto.getUserId()+"already exist");
			}
		}*/
		
		return convertUserEntityToOutputDto(this.usersRepository.save(convertUserInputDtoToEntity(userDto)));
	}

	@Override
	public LoanOutputDto applyLoan(Long customerId, LoanDto loanDto) {
		
		Loan loanApplied = this.loanRepository.save(convertLoanInputDtoToEntity(customerId, loanDto));
		return convertLoanEntityToOutputDto(loanApplied, loanDto);
	}

	@Override
	public LoanOutputDto getStatus(Long loanAppId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LoanOutputDto> getStatusAll(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
