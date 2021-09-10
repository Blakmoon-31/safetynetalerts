package com.openclassrooms.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynetalerts.dto.ChildAlertDto;
import com.openclassrooms.safetynetalerts.dto.ChildrenDto;
import com.openclassrooms.safetynetalerts.dto.FireDto;
import com.openclassrooms.safetynetalerts.dto.FloodDto;
import com.openclassrooms.safetynetalerts.dto.OtherMembersDto;
import com.openclassrooms.safetynetalerts.dto.PersonInfoDto;
import com.openclassrooms.safetynetalerts.dto.StationDto;
import com.openclassrooms.safetynetalerts.dto.StationPersonCountDto;
import com.openclassrooms.safetynetalerts.dto.StationPersonDto;
import com.openclassrooms.safetynetalerts.model.FireStation;
import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.repository.FireStationRepository;
import com.openclassrooms.safetynetalerts.repository.PersonRepository;

@Service
public class MapDtoService {

	@Autowired
	private MedicalRecordService medicalRecordService;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private FireStationRepository fireStationRepository;

	@Autowired
	private DataSafetyNetService dataSafetyNetService;

	public List<FireDto> getFireListForAnAddress(String address) {
		return ((List<Person>) personRepository.findByAddress(address)).stream().map(this::convertToFireDto)
				.collect(Collectors.toList());
	}

	private FireDto convertToFireDto(Person person) {
		FireDto fireDto = new FireDto();

		MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord(person.getFirstName(),
				person.getLastName());
		FireStation station = fireStationRepository.findByAddress(person.getAddress());

		fireDto.setFirstName(person.getFirstName());
		fireDto.setLastName(person.getLastName());
		fireDto.setPhone(person.getPhone());

		fireDto.setAllergies(medicalRecord.getAllergies());
		fireDto.setMedications(medicalRecord.getMedications());

		fireDto.setAge(medicalRecordService.calculateAge(medicalRecord.getBirthdate()));

		fireDto.setStation(station.getStation());

		return fireDto;
	}

	public List<FloodDto> getFloodListForAListOfStations(List<String> stationList) {
		return ((List<Person>) dataSafetyNetService.getPersonsForAListOfStation(stationList)).stream()
				.map(this::convertToFloodDto).collect(Collectors.toList());
	}

	private FloodDto convertToFloodDto(Person person) {
		FloodDto floodDto = new FloodDto();

		MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord(person.getFirstName(),
				person.getLastName());

		floodDto.setFirstName(person.getFirstName());
		floodDto.setLastName(person.getLastName());
		floodDto.setPhone(person.getPhone());

		floodDto.setAllergies(medicalRecord.getAllergies());
		floodDto.setMedications(medicalRecord.getMedications());

		floodDto.setAge(medicalRecordService.calculateAge(medicalRecord.getBirthdate()));

		return floodDto;
	}

	public List<PersonInfoDto> getPersonInfoByFirstNameAndLastName(String firstName, String lastName) {
		if (firstName != null && lastName != null) {
			Person person = personRepository.findByFirstNameAndLastName(firstName, lastName);
			List<PersonInfoDto> personInfoDtoList = new ArrayList<PersonInfoDto>();

			personInfoDtoList.add(convertToPersonInfoDto(person));
			return personInfoDtoList;

		} else {
			if (firstName != null && lastName == null) {
				return ((List<Person>) personRepository.findByFirstName(firstName)).stream()
						.map(this::convertToPersonInfoDto).collect(Collectors.toList());
			} else {
				if (firstName == null && lastName != null) {
					return ((List<Person>) personRepository.findByLastName(lastName)).stream()
							.map(this::convertToPersonInfoDto).collect(Collectors.toList());
				} else {
					return ((List<Person>) personRepository.findAll()).stream().map(this::convertToPersonInfoDto)
							.collect(Collectors.toList());
				}
			}

		}

	}

	private PersonInfoDto convertToPersonInfoDto(Person person) {
		PersonInfoDto personInfoDto = new PersonInfoDto();

		MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord(person.getFirstName(),
				person.getLastName());

		personInfoDto.setFirstName(person.getFirstName());
		personInfoDto.setLastName(person.getLastName());
		personInfoDto.setEmail(person.getEmail());
		personInfoDto.setAddress(person.getAddress());

		personInfoDto.setAllergies(medicalRecord.getAllergies());
		personInfoDto.setMedications(medicalRecord.getMedications());

		personInfoDto.setAge(medicalRecordService.calculateAge(medicalRecord.getBirthdate()));

		return personInfoDto;
	}

	public ChildAlertDto getChildAlertListForAnAddress(String address) {
		ChildAlertDto childAlert = new ChildAlertDto();

		// Get childs at the address
		List<ChildrenDto> childrenList = ((List<Person>) personRepository.findByAddress(address)).stream()
				.map(this::convertToChildrenDto).collect(Collectors.toList());
		// Remove null entries
		childrenList.removeIf(f -> (f.getFirstName() == null));
		// Test if there is at least one child, if yes continue with other members
		if (childrenList.size() > 0) {
			childAlert.setChildren(childrenList);

			List<OtherMembersDto> otherMembersList = ((List<Person>) personRepository.findByAddress(address)).stream()
					.map(this::convertToOtherMembersdDto).collect(Collectors.toList());
			otherMembersList.removeIf(f -> (f.getFirstName() == null));
			childAlert.setOtherMembers(otherMembersList);
		}
		return childAlert;
	}

	private ChildrenDto convertToChildrenDto(Person person) {
		ChildrenDto childrenDto = new ChildrenDto();

		MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord(person.getFirstName(),
				person.getLastName());
		int age = medicalRecordService.calculateAge(medicalRecord.getBirthdate());

		if (age <= 18) {
			childrenDto.setFirstName(person.getFirstName());
			childrenDto.setLastName(person.getLastName());
			childrenDto.setAge(age);
		}

		return childrenDto;
	}

	private OtherMembersDto convertToOtherMembersdDto(Person person) {
		OtherMembersDto otherMembersDto = new OtherMembersDto();

		MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord(person.getFirstName(),
				person.getLastName());
		int age = medicalRecordService.calculateAge(medicalRecord.getBirthdate());

		if (age > 18) {
			otherMembersDto.setFirstName(person.getFirstName());
			otherMembersDto.setLastName(person.getLastName());
		}

		return otherMembersDto;
	}

	public StationDto getListForAStation(String stationNumber) {
		StationDto stationDto = new StationDto();

		List<StationPersonDto> stationPersonDto = ((List<Person>) dataSafetyNetService
				.getPersonsForAStation(stationNumber)).stream().map(this::convertToStationPersonDto)
						.collect(Collectors.toList());
		stationDto.setPersons(stationPersonDto);

		StationPersonCountDto stationCountDto = new StationPersonCountDto();
		int childCount = 0;
		int adultCount = 0;

		for (StationPersonDto p : stationPersonDto) {
			MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord(p.getFirstName(), p.getLastName());

			if (medicalRecordService.calculateAge(medicalRecord.getBirthdate()) <= 18) {
				childCount++;
			} else {
				adultCount++;
			}
		}
		stationCountDto.setChildren(childCount);
		stationCountDto.setAdults(adultCount);
		stationDto.setCount(stationCountDto);

		return stationDto;
	}

	private StationPersonDto convertToStationPersonDto(Person person) {
		StationPersonDto stationPersonDto = new StationPersonDto();

		stationPersonDto.setFirstName(person.getFirstName());
		stationPersonDto.setLastName(person.getLastName());
		stationPersonDto.setAddress(person.getAddress());
		stationPersonDto.setPhone(person.getPhone());

		return stationPersonDto;
	}
}
