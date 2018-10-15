package com.rajesh.equipment.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.rajesh.equipment.model.Equipment;
import com.rajesh.equipment.model.ServiceResponse;
import com.rajesh.equipment.util.CloudantDBFactory;

@ApplicationPath("api")
@Path("/equipment")
public class EquipmentService 
{
	final static Logger logger = Logger.getLogger(EquipmentService.class);	
	ServiceResponse response = new ServiceResponse();	
	Gson gson = new Gson();
	
	
	//GET REST API Request to fetch X Equipments, where X is number of records to fetch
	
	@GET
	@Path("/search")
    @Produces({"application/json"})
	public String fetchByLimit(@QueryParam("limit") int searchLimit) 
	{
		logger.info("Search limit :: "+searchLimit);
		
		List<Equipment> equipments = null;
		try 
		{
			equipments = CloudantDBFactory.getDatabase().getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Equipment.class);
			
			if(searchLimit > equipments.size())
			{
				response.setStatus(400);
				response.setMessage("Search limit exceeded than database result... Documents count:: "+equipments.size());
			}
			else if(searchLimit == 0)
			{
				response.setStatus(200);
				response.setMessage("Success");
				response.setData(equipments);
			}
			else
			{
				response.setStatus(200);
				response.setMessage("Success");
				response.setData(equipments.subList(0, searchLimit));	
			}						
		} 
		catch (Exception e) 
		{
			response.setStatus(400);
			response.setMessage(e.getMessage());
		}
		
		logger.info("Response::: "+response.toString());
		
		return gson.toJson(response);
	}
	
	
	// GET REST API Request to fetch all records or any particular record with unique number (equipment_number)
	
	@GET
	@Path("{equipmentNumber}")
    @Produces({"application/json"})
	public String getUserByName(@PathParam("equipmentNumber") String equipment_number) 
	{		
		logger.info("equipmentNumber :: "+equipment_number);
		
		List<Equipment> equipments = null;
		try 
		{
			if(null != equipment_number)
			{
				Equipment equipment = CloudantDBFactory.getDatabase().find(Equipment.class, equipment_number);
				
				if(null != equipment)
				{
					equipments = new ArrayList<Equipment>();
					equipments.add(equipment);
					
					response.setStatus(200);
					response.setMessage("Success");
					response.setData(equipments);
				}
				else
				{
					response.setStatus(404);
					response.setMessage("Equipment does not exist...");
				}
			}
			else
			{
				equipments = CloudantDBFactory.getDatabase().getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Equipment.class);
				
				if(equipments.isEmpty())
				{
					response.setStatus(404);
					response.setMessage("Equipment does not exist...");
				}
				else
				{
					response.setStatus(200);
					response.setMessage("Success");
					response.setData(equipments);
				}
			}				
						
		} 
		catch (Exception e) 
		{
			response.setStatus(400);
			response.setMessage(e.getMessage());
		}
		
		logger.info("Response::: "+response.toString());
		
		return gson.toJson(response);
	}
	
	
	//POST Rest API implementation to create the equipment record in database
	
	@POST
	@Consumes({"application/json"})
    @Produces({"application/json"})
	public String createEquipment(Equipment equipment)
	{
		logger.info("Equipment :: "+equipment.toString());
		
		try 
		{
			if(null == equipment.getEqipment_number())
			{
				response.setStatus(400);
				response.setMessage("Eqipment_number cannot be null....");
			}			
			else if(null == CloudantDBFactory.getDatabase().find(Equipment.class, equipment.getEqipment_number()))
			{
				CloudantDBFactory.getDatabase().save(equipment);
				
				response.setStatus(201);
				response.setMessage("Equipment Successfully Created...");
			}
			else
			{
				response.setStatus(400);
				response.setMessage("Equipment \"+equipment.getEqipment_number()+\" is already exist in system...");
			}				
		} 
		catch (Exception e) 
		{
			response.setStatus(400);
			response.setMessage(e.getMessage());
		}
		
		logger.info("Response::: "+response.toString());
		
		return gson.toJson(response);		
	}

}
