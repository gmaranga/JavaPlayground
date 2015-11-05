package com.springapp.web.service;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PriceIncrease {

    /** Logger for this class and subclasses */
    protected final Log LOG = LogFactory.getLog(getClass());
  
    @Max(value=50, message="{Max.priceincrease.percentage}")
    @Min(value=0, message="{Min.priceincrease.percentage}")
    private int percentage;

    public void setPercentage(int i) {
        percentage = i;
        LOG.info("Percentage set to " + i);
    }

    public int getPercentage() {
        return percentage;
    }

}
