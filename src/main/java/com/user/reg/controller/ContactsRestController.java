package com.user.reg.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.user.reg.domain.Contact;
import com.user.reg.domain.ContactJson;
import com.user.reg.domain.ContactType;
import com.user.reg.domain.User;
import com.user.reg.service.ContactService;
import com.user.reg.service.UserService;
import com.user.reg.utils.ImageEncoderUtil;
import com.user.reg.utils.JsonConverterUtil;

@RestController
@RequestMapping("/rest")
public class ContactsRestController {
	
	private Logger logger = Logger.getLogger(ContactsRestController.class);

	@Autowired
	private ContactService contactService;

	@Autowired
	private UserService userService;

	@Autowired
	private JsonConverterUtil<Contact, ContactJson> contactConverter;

	@RequestMapping(value = "/contact/add", method = RequestMethod.PUT)
	public void addContact(@RequestBody ContactJson contactJson, HttpServletRequest request) {
		Contact contact = contactCreate(contactJson);
		long ownerUserId = contactJson.getOwnerUserId();
		long contactId = contactService.add(contact);
		
		if(contactJson.getImageEncode64() != null) {
			try {
				addImageToResources(request, ownerUserId, contactId, 
						contactJson.getImageEncode64());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/contact/update", method = RequestMethod.PUT)
	public void updateContact(@RequestBody ContactJson contactJson,
			HttpServletRequest request) {
		Contact contact = contactUpdate(contactJson);
		long ownerUserId = contactJson.getOwnerUserId();
		long contactId = contactJson.getId();
		contactService.update(contact);
		
		if(contactJson.getImageEncode64() != null) {
			File file = new File(getImageLocation(request, ownerUserId, contactId));
			
			if(file.exists()) {
				file.delete();
			}
			
			try {
				addImageToResources(request, ownerUserId, contactId,
						contactJson.getImageEncode64());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		contactService.update(contact);
	}

	@RequestMapping(value = "/contact/delete/{contactId}", method = RequestMethod.POST)
	public void deleteContact(@PathVariable Long contactId) {
		contactService.delete(contactService.get(contactId));
	}
	
	@RequestMapping(value = "/contact/contactypes", method = RequestMethod.GET)
	public ContactType[] getContactTypes() {
		return ContactType.values();
	}

	@RequestMapping(value = "/contact/get/{userId}", method = RequestMethod.GET)
	public List<ContactJson> getContactsByUserId(@PathVariable Long userId) {
		List<ContactJson> contacts = null;

		try {
			contacts = contactConverter.convertToJsons(
					contactService.getContactsByUserId(userId), 
					ContactJson.class);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return contacts;
	}
	        
    private void addImageToResources(HttpServletRequest request,
    		long ownerUserId, long contactId, String imageEncode64) throws IOException {
    	
    	BufferedImage image = ImageEncoderUtil.getImageFromEncode64(
				imageEncode64);
		String imageLocation = getImageLocation(request, ownerUserId, 
				contactId);
		File file = new File(imageLocation);
		uploadImage(image, file);
		logger.info("Image uploaded: " + file.getAbsolutePath());
    }
    
    private String getImageLocation(HttpServletRequest request, 
    		long ownerUserId, long contactId) {
    	return getImageContactResources(request) 
    			+ generateContactImageName(ownerUserId, contactId);
    }
    
    private String getImageContactResources(HttpServletRequest request) {
    	String rootDirectory = request.getSession().getServletContext()
    			.getRealPath("/");
    	
    	return rootDirectory + "resources\\images\\";
    }
    
    private String generateContactImageName(long ownerUserId, long contactId) {
    	return "contact_" + ownerUserId + "_" + contactId + ".png";
    }
    
    private void uploadImage(BufferedImage image, File file) throws IOException {
    	ImageIO.write(image, "png", file);
    }
    
    private Contact contactUpdate(ContactJson contactJson) {
		Contact contact = contactService.get(contactJson.getId());
		setContact(contact, contactJson);

		return contact;
	}

	private Contact contactCreate(ContactJson contactJson) {
		Contact contact = new Contact();
		User user = userService.get(contactJson.getOwnerUserId());
		setContact(contact, contactJson);
		contact.setUser(user);	
		
		return contact;
	}
	
	private void setContact(Contact contact, ContactJson contactJson) {
		contact.setFirstName(contactJson.getFirstName());
		contact.setLastName(contactJson.getLastName());
		contact.setPhoneNumber(contactJson.getPhoneNumber());
		contact.setEmail(contactJson.getEmail());
		contact.setContactType(contactJson.getContactType());	
	}
	
}
