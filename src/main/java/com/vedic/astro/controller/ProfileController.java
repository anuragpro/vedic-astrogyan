package com.vedic.astro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vedic.astro.dto.LevelProfileAspectDTO;
import com.vedic.astro.dto.PathProfileAspectDTO;
import com.vedic.astro.dto.PredictionOutcomeDTO;
import com.vedic.astro.dto.PredictionTemplateDTO;
import com.vedic.astro.dto.ProfileAspectDTO;
import com.vedic.astro.dto.ProfileFilterDTO;
import com.vedic.astro.dto.ProfileFilterTypeDTO;
import com.vedic.astro.dto.TemplateAspectDTO;
import com.vedic.astro.enums.MemberType;
import com.vedic.astro.exception.BusinessException;
import com.vedic.astro.exception.SystemException;
import com.vedic.astro.service.ProfileService;

/**
 * The Main controller which handles all the incoming GET and POST RESTFul web
 * service calls.
 * 
 * @author Sumeer Saxena
 */
@RestController
@RequestMapping("/api")
final public class ProfileController extends BaseController {

	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;

	@RequestMapping(value = "/profile/tree/{memberType}", method = RequestMethod.GET)
	public RestServiceResponse<List<ProfileAspectDTO>> 
		getProfileHeirarchy(@PathVariable MemberType memberType) 
				throws BusinessException, SystemException {

		List<ProfileAspectDTO> profileHeirarchy = profileService.getProfileHierachyTree(false, null, memberType);

		return new RestServiceResponse<List<ProfileAspectDTO>>(profileHeirarchy);
	}

	@RequestMapping(value = "/profile/info/{memberType}", method = RequestMethod.GET)
	public RestServiceResponse<List<ProfileAspectDTO>> 
		getProfileHeirarchyInfo(@PathVariable MemberType memberType)
			throws BusinessException, SystemException {

		List<ProfileAspectDTO> profileHeirarchy = profileService.getProfileHierachyTree(true, null, memberType);

		return new RestServiceResponse<List<ProfileAspectDTO>>(profileHeirarchy);
	}

	@RequestMapping(value = "/profile/filter/{memberType}", method = RequestMethod.POST)
	public RestServiceResponse<List<ProfileAspectDTO>> getProfileHeirarchyFilter(
			@RequestBody @Valid ProfileFilterDTO profileFilterDTO, @PathVariable MemberType memberType) throws BusinessException, SystemException {

		List<ProfileAspectDTO> profileHeirarchy = 
				profileService.getProfileHierachyTree(true, profileFilterDTO, memberType);

		return new RestServiceResponse<List<ProfileAspectDTO>>(profileHeirarchy);
	}
	
	@RequestMapping(value = "/profile/filter", method = RequestMethod.POST)
	public RestServiceResponse<List<ProfileAspectDTO>> getProfileHeirarchyFilter(
			@RequestBody @Valid ProfileFilterDTO profileFilterDTO) throws BusinessException, SystemException {

		List<ProfileAspectDTO> profileHeirarchy = 
				profileService.getMemberProfileHierachyTree(true, profileFilterDTO);

		return new RestServiceResponse<List<ProfileAspectDTO>>(profileHeirarchy);
	}

	@RequestMapping(value = "/profile/flat/{memberType}", method = RequestMethod.GET)
	public RestServiceResponse<List<PathProfileAspectDTO>> getProfileHeirarchyFlat(@PathVariable MemberType memberType)
			throws BusinessException, SystemException {
		List<PathProfileAspectDTO> profileHeirarchy = profileService.getProfileHierachyFlat(memberType);

		return new RestServiceResponse<List<PathProfileAspectDTO>>(profileHeirarchy);
	}

	@RequestMapping(value = "/profile/parents/{memberType}", method = RequestMethod.GET)
	public RestServiceResponse<List<LevelProfileAspectDTO>> getAllParents(@PathVariable MemberType memberType)
			throws BusinessException, SystemException {

		List<LevelProfileAspectDTO> parentsList = profileService.getAllParents(memberType);

		return new RestServiceResponse<List<LevelProfileAspectDTO>>(parentsList);
	}

	@RequestMapping(value = "/profile/children/{memberType}/{parentCode}", method = RequestMethod.GET)
	public RestServiceResponse<List<LevelProfileAspectDTO>> getChildren(@PathVariable MemberType memberType,
			@PathVariable String parentCode) throws BusinessException, SystemException {
		List<LevelProfileAspectDTO> children = profileService.getImmediateChildren(parentCode, memberType);
		return new RestServiceResponse<List<LevelProfileAspectDTO>>(children);
	}

	@RequestMapping(value = "/profile/save", method = RequestMethod.POST)
	public RestServiceResponse<String> addAspect(@RequestBody @Valid LevelProfileAspectDTO profileAspectDTO)
			throws BusinessException, SystemException {

		profileService.saveAspect(profileAspectDTO);
		String returnValue = "Aspect saved successfully";
		return new RestServiceResponse<String>(returnValue);
	}

	@RequestMapping(value = "/profile/delete/{id}", method = RequestMethod.POST)
	public RestServiceResponse<String> deleteAspect(@PathVariable String id) throws BusinessException, SystemException {

		profileService.deleteAspect(id);
		String returnValue = "Aspect deleted successfully";

		return new RestServiceResponse<String>(returnValue);
	}

	@RequestMapping(value = "/template/save", method = RequestMethod.POST)
	public RestServiceResponse<String> addTemplate(@RequestBody @Valid PredictionTemplateDTO predictionTemplateDTO)
			throws BusinessException, SystemException {

		profileService.saveTemplate(predictionTemplateDTO);
		String returnValue = "Template saved successfully";
		return new RestServiceResponse<String>(returnValue);
	}

	@RequestMapping(value = "/template/delete", method = RequestMethod.POST)
	public RestServiceResponse<String> deleteTemplate(@RequestBody @Valid PredictionTemplateDTO predictionTemplateDTO)
			throws BusinessException, SystemException {

		profileService.deleteTemplate(predictionTemplateDTO);
		String returnValue = "Template deleted successfully";
		return new RestServiceResponse<String>(returnValue);
	}

	@RequestMapping(value = "/templates/all", method = RequestMethod.GET)
	public RestServiceResponse<List<PredictionTemplateDTO>> getAllTemplates()
			throws BusinessException, SystemException {

		List<PredictionTemplateDTO> predictionTemplateDTOList = profileService.getAll();
		return new RestServiceResponse<List<PredictionTemplateDTO>>(predictionTemplateDTOList);
	}

	@RequestMapping(value = "/template/aspects/{memberType}/{templateCode}", method = RequestMethod.GET)
	public RestServiceResponse<List<TemplateAspectDTO>> getTemplateAspects(
				@PathVariable MemberType memberType, 
				@PathVariable String templateCode)
			throws BusinessException, SystemException {

		List<TemplateAspectDTO> templateAspectDTOList = 
				profileService.getAllAspects(templateCode, memberType);
		return new RestServiceResponse<List<TemplateAspectDTO>>(templateAspectDTOList);
	}

	@RequestMapping(value = "/outcome/save", method = RequestMethod.POST)
	public RestServiceResponse<String> saveOutcome(@RequestBody @Valid PredictionOutcomeDTO predictionOutcomeDTO)
			throws BusinessException, SystemException {

		profileService.savePredictionOutcome(predictionOutcomeDTO);
		String returnValue = "Outcome saved successfully";
		return new RestServiceResponse<String>(returnValue);
	}

	@RequestMapping(value = "/outcome/create", method = RequestMethod.POST)
	public RestServiceResponse<String> createOutcome(@RequestBody @Valid PredictionOutcomeDTO predictionOutcomeDTO)
			throws BusinessException, SystemException {

		profileService.createPredictionOutcome(predictionOutcomeDTO);
		String returnValue = "Outcome created successfully";
		return new RestServiceResponse<String>(returnValue);
	}

	@RequestMapping(value = "/outcome/delete", method = RequestMethod.POST)
	public RestServiceResponse<String> deleteOutcome(@RequestBody @Valid PredictionOutcomeDTO predictionOutcomeDTO)
			throws BusinessException, SystemException {

		profileService.deleteOutcome(predictionOutcomeDTO);
		String returnValue = "Outcome deleted successfully";
		return new RestServiceResponse<String>(returnValue);
	}

	@RequestMapping(value = "/outcomes/{memberType}/{templateCode}", method = RequestMethod.GET)
	public RestServiceResponse<List<PredictionOutcomeDTO>> getOutcomes(@PathVariable MemberType memberType, @PathVariable String templateCode)
			throws BusinessException, SystemException {

		List<PredictionOutcomeDTO> predictionOutcomeDTOList = profileService.getOutcomes(templateCode, memberType);
		return new RestServiceResponse<List<PredictionOutcomeDTO>>(predictionOutcomeDTOList);
	}
}
