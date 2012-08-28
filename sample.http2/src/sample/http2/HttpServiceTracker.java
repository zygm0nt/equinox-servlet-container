package sample.http2;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;

public class HttpServiceTracker extends ServiceTracker {

	public HttpServiceTracker(BundleContext context) {
		super(context, HttpService.class.getName(), null);
	}

	public Object addingService(ServiceReference reference) {
		HttpService httpService = (HttpService) context.getService(reference);
		try {			
			httpService.registerServlet("/helloworld2", new HelloWorldServlet(), null, null); //$NON-NLS-1$
		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpService;
	}		
	
	public void removedService(ServiceReference reference, Object service) {
		HttpService httpService = (HttpService) service;
		httpService.unregister("/helloworld2"); //$NON-NLS-1$
		super.removedService(reference, service);
	}

}