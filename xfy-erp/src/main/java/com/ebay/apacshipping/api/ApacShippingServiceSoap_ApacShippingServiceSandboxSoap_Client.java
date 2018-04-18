
package com.ebay.apacshipping.api;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-04-21T09:24:23.473+08:00
 * Generated source version: 3.0.4
 * 
 */
public final class ApacShippingServiceSoap_ApacShippingServiceSandboxSoap_Client {
	private static final Logger logger = LoggerFactory
			.getLogger(ApacShippingServiceSoap_ApacShippingServiceSandboxSoap_Client.class);
    private static final QName SERVICE_NAME = new QName("http://api.apacshipping.ebay.com/", "ApacShippingService");

    private ApacShippingServiceSoap_ApacShippingServiceSandboxSoap_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = ApacShippingService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                logger.error(e.getMessage());
            }
        }
      
        ApacShippingService ss = new ApacShippingService(wsdlURL, SERVICE_NAME);
        ApacShippingServiceSoap port = ss.getApacShippingServiceSandboxSoap();  
        
        {
        logger.debug("Invoking getAPACShippingTrackCode...");
        com.ebay.apacshipping.api.GetAPACShippingTrackCodeRequest _getAPACShippingTrackCode_getAPACShippingTrackCodeRequest = null;
        com.ebay.apacshipping.api.GetAPACShippingTrackCodeResponse2 _getAPACShippingTrackCode__return = port.getAPACShippingTrackCode(_getAPACShippingTrackCode_getAPACShippingTrackCodeRequest);
        logger.debug("getAPACShippingTrackCode.result=" + _getAPACShippingTrackCode__return);


        }
        {
        logger.debug("Invoking recreateAPACShippingPackage...");
        com.ebay.apacshipping.api.RecreateAPACShippingPackageRequest _recreateAPACShippingPackage_recreateAPACShippingPackageRequest = null;
        com.ebay.apacshipping.api.RecreateAPACShippingPackageResponse2 _recreateAPACShippingPackage__return = port.recreateAPACShippingPackage(_recreateAPACShippingPackage_recreateAPACShippingPackageRequest);
        logger.debug("recreateAPACShippingPackage.result=" + _recreateAPACShippingPackage__return);


        }
        {
        logger.debug("Invoking getAPACShippingPackageStatus...");
        com.ebay.apacshipping.api.GetAPACShippingPackageStatusRequest _getAPACShippingPackageStatus_getAPACShippingPackageStatusRequest = null;
        com.ebay.apacshipping.api.GetAPACShippingPackageStatusResponse2 _getAPACShippingPackageStatus__return = port.getAPACShippingPackageStatus(_getAPACShippingPackageStatus_getAPACShippingPackageStatusRequest);
        logger.debug("getAPACShippingPackageStatus.result=" + _getAPACShippingPackageStatus__return);


        }
        {
        logger.debug("Invoking getAPACShippingLabels...");
        com.ebay.apacshipping.api.GetAPACShippingLabelsRequest _getAPACShippingLabels_getAPACShippingLabelRequest = null;
        com.ebay.apacshipping.api.GetAPACShippingLabelsResponse2 _getAPACShippingLabels__return = port.getAPACShippingLabels(_getAPACShippingLabels_getAPACShippingLabelRequest);
        logger.debug("getAPACShippingLabels.result=" + _getAPACShippingLabels__return);


        }
        {
        logger.debug("Invoking getAPACShippingPackage...");
        com.ebay.apacshipping.api.GetAPACShippingPackageRequest _getAPACShippingPackage_getAPACShippingPackageRequest = null;
        com.ebay.apacshipping.api.GetAPACShippingPackageResponse2 _getAPACShippingPackage__return = port.getAPACShippingPackage(_getAPACShippingPackage_getAPACShippingPackageRequest);
        logger.debug("getAPACShippingPackage.result=" + _getAPACShippingPackage__return);


        }
        {
        logger.debug("Invoking confirmAPACShippingPackage...");
        com.ebay.apacshipping.api.ConfirmAPACShippingPackageRequest _confirmAPACShippingPackage_confirmAPACShippingPackageRequest = null;
        com.ebay.apacshipping.api.ConfirmAPACShippingPackageResponse2 _confirmAPACShippingPackage__return = port.confirmAPACShippingPackage(_confirmAPACShippingPackage_confirmAPACShippingPackageRequest);
        logger.debug("confirmAPACShippingPackage.result=" + _confirmAPACShippingPackage__return);


        }
        {
        logger.debug("Invoking getAPACShippingRate...");
        com.ebay.apacshipping.api.GetAPACShippingRateRequest _getAPACShippingRate_getAPACShippingRateRequest = null;
        com.ebay.apacshipping.api.GetAPACShippingRateResponse2 _getAPACShippingRate__return = port.getAPACShippingRate(_getAPACShippingRate_getAPACShippingRateRequest);
        logger.debug("getAPACShippingRate.result=" + _getAPACShippingRate__return);


        }
        {
        logger.debug("Invoking getAPACShippingLabel...");
        com.ebay.apacshipping.api.GetAPACShippingLabelRequest _getAPACShippingLabel_getAPACShippingLabelRequest = null;
        com.ebay.apacshipping.api.GetAPACShippingLabelResponse2 _getAPACShippingLabel__return = port.getAPACShippingLabel(_getAPACShippingLabel_getAPACShippingLabelRequest);
        logger.debug("getAPACShippingLabel.result=" + _getAPACShippingLabel__return);


        }
        {
        logger.debug("Invoking cancelAPACShippingPackage...");
        com.ebay.apacshipping.api.CancelAPACShippingPackageRequest _cancelAPACShippingPackage_cancelAPACShippingPackageRequest = null;
        com.ebay.apacshipping.api.CancelAPACShippingPackageResponse2 _cancelAPACShippingPackage__return = port.cancelAPACShippingPackage(_cancelAPACShippingPackage_cancelAPACShippingPackageRequest);
        logger.debug("cancelAPACShippingPackage.result=" + _cancelAPACShippingPackage__return);


        }
        {
        logger.debug("Invoking addAPACShippingPackage...");
        com.ebay.apacshipping.api.AddAPACShippingPackageRequest _addAPACShippingPackage_addAPACShippingPackageRequest = null;
        com.ebay.apacshipping.api.AddAPACShippingPackageResponse2 _addAPACShippingPackage__return = port.addAPACShippingPackage(_addAPACShippingPackage_addAPACShippingPackageRequest);
        logger.debug("addAPACShippingPackage.result=" + _addAPACShippingPackage__return);


        }
        {
        logger.debug("Invoking verifyAPACShippingUser...");
        com.ebay.apacshipping.api.VerifyAPACShippingUserRequest _verifyAPACShippingUser_verifyAPACShippingUserRequest = null;
        com.ebay.apacshipping.api.VerifyAPACShippingUserResponse2 _verifyAPACShippingUser__return = port.verifyAPACShippingUser(_verifyAPACShippingUser_verifyAPACShippingUserRequest);
        logger.debug("verifyAPACShippingUser.result=" + _verifyAPACShippingUser__return);


        }

        System.exit(0);
    }

}
