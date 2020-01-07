package controllers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.MDC;

import com.google.common.base.Stopwatch;

import play.Logger;
import play.libs.Codec;
import play.mvc.After;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;

public class TrackerController extends Controller{

	    @Before
	    static void before() {
	        Logger.info("----------------------");
	        Logger.info("nav - %s", request.url);

	    }
}
