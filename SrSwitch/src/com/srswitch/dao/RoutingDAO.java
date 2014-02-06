package com.srswitch.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.srswitch.exception.BusinessException;
import com.srswitch.exception.SystemException;
import com.srswitch.model.Cardrouting;
import com.srswitch.model.Registration;
import com.srswitch.model.Serviceendpoint;

/**
 * @author nitin.malik
 *
 *	DAO Layer implementation responsible for performing 
 *	Users related database operations.
 *
 */
@Repository
public class RoutingDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoutingDAO.class);
	
	@PersistenceContext(unitName = "switch_DS")
	EntityManager entityManager;
	
	
	public void display(){
		System.out.println("Display method called");
	}
	
	/**
	 * Return Registration details based on msidn,imei and imsi number.
	 * 
	 * @param msisdn
	 * @param imeiNum
	 * @param imsiNum
	 * @return
	 */
	public Registration getRegistrationDetails(String msisdn, String imeiNum, String imsiNum)throws BusinessException{
		
		Registration registration = null;
		try {
			TypedQuery<Registration> query = entityManager.createNamedQuery("Registration.findByMsisdnImeiImsi", Registration.class);
			query.setParameter("msisdn", msisdn);
			query.setParameter("imeiNum", imeiNum);
			query.setParameter("imsiNum", imsiNum);
			
			registration = query.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.error("No Registration found for Terminal having \n MSISDN : "
					+ msisdn
					+ " \n IMSI : "
					+ imsiNum
					+ " \n IMEI : "
					+ imeiNum);
			throw new BusinessException("reg.term.nodata");
		}
		return registration;
	}
	 
	
	/**
	 * Returns the list of configured bin ranges
	 * 
	 * @return 
	 */
	public List<Cardrouting> getConfiguredBinRanges()throws SystemException{

		List<Cardrouting> binRanges = null;
		TypedQuery<Cardrouting> query = entityManager.createNamedQuery("Cardrouting.findAll", Cardrouting.class);

		try {
			binRanges = query.getResultList();
		} catch (NoResultException e) {
			LOGGER.error("Error occured while getting configured bin range : " ,e);
			throw new SystemException("Error occured while getting configured bin ranges");
		}
		return binRanges;
	}
	
	
	/**
	 * Returns the list of configured service endpoints
	 * 
	 * @return
	 */
	public List<Serviceendpoint> getConfiguredServiceEndpoints()throws SystemException{
		
		List<Serviceendpoint> serviceEndpoints = null;
		TypedQuery<Serviceendpoint> query = entityManager.createNamedQuery("Serviceendpoint.findAll", Serviceendpoint.class);
		
		try{
			serviceEndpoints = query.getResultList();
		}catch(NoResultException exp){
			LOGGER.error("Error while getting Configured Service Endpoints" , exp);
			throw new SystemException("Error while getting Configured Service Endpoints");
		}
		return serviceEndpoints;
	}
	
}
