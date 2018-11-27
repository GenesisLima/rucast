package org.ntvru.rucast.controller;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Produces;

import org.ntvru.rucast.model.Episode;
import org.ntvru.rucast.model.Show;
import org.ntvru.rucast.rss.AudiocastRSSViewer;
import org.ntvru.rucast.service.EpisodeService;
import org.ntvru.rucast.service.FileService;
import org.ntvru.rucast.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Genesis Lima
 */
@Controller
public class FeedofShowController {

	@Autowired
	private FileService fileService;
	
	@Autowired
	private ShowService showService;
	
	
	

	
	@Autowired
	private EpisodeService episodeService;
	
	//TODO Do Refactorings with Optional API
	@RequestMapping(value="/rssfeed/{showName}", method=RequestMethod.GET)
	public ModelAndView getFeedRssByName(Episode episode, @PathVariable("showName") String showName) throws UnknownHostException{
		ModelAndView modelAndView=  null;
	    Optional<Show> theShow = showService.findShowByName(showName);
	   if(theShow.isPresent()) {
	   List<Episode> episodes = theShow.get().getEpisodes();
	   System.out.println("EPISODE "+episodes);
	   modelAndView = new ModelAndView(new AudiocastRSSViewer(), "feedEpisodes", episodes);
	    return modelAndView;
	   }
        modelAndView  = new ModelAndView("not_found", "message","Feed não Encontrado! :(");
	   return modelAndView;
		
	
	}
	
	@RequestMapping(value="/rssfeed.*", method=RequestMethod.GET)
	@Produces("application/rss+xml")
	public @ResponseBody ModelAndView getFeedRss() throws Exception{		  
		
		
		List<Episode> items = episodeService.findAll();	
		
		ModelAndView modelAndView = new ModelAndView(new AudiocastRSSViewer(), "feedEpisodes",items);
	    
//		View resolvedView = view.resolveViewName("rssfeed", Locale.getDefault());
//		MockHttpServletResponse mockResp = new MockHttpServletResponse();
//		resolvedView.render(modelAndView.getModel(), request, mockResp);
//		System.out.println("rendered html : " + mockResp.getContentAsString());
		//System.out.println("RSS VIEW "+modelAndView.getModel().());
		
		return modelAndView;
	}
	
	
}
