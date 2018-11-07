package org.ntvru.rucast.rss;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ntvru.rucast.model.Episode;
import org.ntvru.rucast.model.Show;
import org.ntvru.rucast.utils.SyndicationLink;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.rometools.modules.atom.modules.AtomLinkModuleImpl;
import com.rometools.modules.itunes.EntryInformation;
import com.rometools.modules.itunes.EntryInformationImpl;
import com.rometools.modules.itunes.FeedInformation;
import com.rometools.modules.itunes.FeedInformationImpl;
import com.rometools.modules.itunes.types.Category;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Enclosure;
import com.rometools.rome.feed.rss.Guid;
import com.rometools.rome.feed.rss.Image;
import com.rometools.rome.feed.rss.Item;

/**
 * Created by Genesis Lima
 */
public class AudiocastRSSViewer extends AbstractRssFeedView {
	
	
	 List<Module> modules = new ArrayList<Module>();
	 FeedInformation feedInfo = new FeedInformationImpl( );
	 EntryInformation e = new EntryInformationImpl();
	 List<Category> categories = new ArrayList<Category>();
//	 Category educationCategory = new Category("Education");		 
//	 Category politicsCategory = new Category("Politics");
	 List<Item> items = new ArrayList<Item>();
	 
	 
	 public AudiocastRSSViewer() {
		 categories.add(new Category("Education"));
		 feedInfo.setCategories(categories);
		
	}
	 


	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model,
		 HttpServletRequest arg1, HttpServletResponse arg2) throws Exception {
		 @SuppressWarnings("unchecked")
		List<Episode> listEps = (List<Episode>) model.get("feedEpisodes");
		 
	
		 
		
		 for(Episode episode:listEps){
		 
			 Item item = new Item();
			// syndEntry = new SyndEntryImpl();
			// syndDescription = new SyndContentImpl();
			 Content content = new Content();
//           
//		     
//		    
//			 
			 content.setValue(episode.getSynopsis());
//			 
			 item.setContent(content);			
			 item.setTitle(episode.getTopic());			
			 item.setLink(episode.getFileDocument().getUri()+episode.getId());
			 Guid guid = new Guid();
			 guid.setValue(episode.getFileDocument().getUri()+episode.getId());
			 guid.setPermaLink(true);
			 item.setGuid(guid);
			
			 Description description = new Description();
			 description.setValue(episode.getSynopsis());
             item.setDescription(description);
             item.setPubDate(new Date());
           
            
//             e.setSubtitle(show.getTopic());
//             e.setDuration(new Duration(4182295));
//             e.setAuthor("NTVRU-UFPE");
//             e.setImage(new URL("http://estudante.ufpe.br/wp-content/themes/portaldoestudante/images/logo_UFPE.png"));
//             e.setSummary(show.getSynopsis());
//           
//             e.setKeywords(new String[]{"Educacao","Politico"});
            
             //FeedInfo was here
             
            
           
//             feedInfo.setImage(new URL("http://estudante.ufpe.br/wp-content/themes/portaldoestudante/images/logo_UFPE.png"));
//             
//            
             feedInfo.setSummary(episode.getSynopsis());
             feedInfo.setSubtitle("Subtitulo");
//             feedInfo.setCategories(categories);
            feedInfo.setKeywords(new String[]{"Educacao"});
//           feedInfo.setAuthor("Nucleo de TV e Radio Universitaria");
//             
//             syndEntry.setTitle(show.getTopic());
//             syndEntry.setLink(show.getUrl());
             
			
             Enclosure enclosure = new Enclosure();
//            
             enclosure.setUrl(episode.getFileDocument().getUri()+episode.getId());
             enclosure.setType(episode.getFileDocument().getFileType());
//             
             enclosure.setLength(4182295);
             item.getEnclosures().add(enclosure);
//             
//             item.setModules(modules);
            
          // syndDescription.setType(show.getFileDocument().getFileType());
           //  syndDescription.setValue(show.getSynopsis());
           //  syndEntry.setDescription(syndDescription);
             
           
			items.add(item);
			
			
			 
		 }
		// modules.add(e);
		
		
		
		 
		 
		return items;
	}
	
	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
			HttpServletRequest request) {
		final List<Episode> listEpisodes = (List<Episode>) model.get("feedEpisodes");

		
		feed.setTitle("NTVRU-RSS");
		feed.setDescription("Este feed distribui os produtos de �udio do N�cleo de TV e R�dio Universit�ria da UFPE");
		feed.setLink("http://estudante.ufpe.br/wp-content/themes/portaldoestudante/images/logo_UFPE.png");
		feed.setWebMaster("genesis.lima@ufpe.br (Genesis Lima)");
		feed.setEncoding("UTF-8");
		feed.setLanguage("pt-br");
		feed.setModules(modules);
		Image ufpeImage = new Image();
		ufpeImage.setTitle("NTVRU-RSS");
		ufpeImage.setWidth(50);
		ufpeImage.setHeight(50);
		ufpeImage.setLink("http://estudante.ufpe.br/wp-content/themes/portaldoestudante/images/logo_UFPE.png");
		ufpeImage.setUrl("http://estudante.ufpe.br/wp-content/themes/portaldoestudante/images/logo_UFPE.png");
		feed.setImage(ufpeImage);
		  feedInfo.setCategories(categories);
		  feedInfo.setKeywords(new String[]{"Educa��o","Pol�tico"});
		  feedInfo.setOwnerName("NTVRU-UFPE");
          feedInfo.setOwnerEmailAddress("genesis.lima@ufpe.br (Genesis Lima)");
          feedInfo.setSummary("Este feed distribui os produtos de �udio do N�cleo de TV e R�dio Universit�ria da UFPE");
          feed.getModules().add(feedInfo);
         
          //begin atom
          final List<Link> atomLinks = new ArrayList<>();
         // atomLinks.add(new SyndicationLink().withRel("self").withType(super.getContentType()).withHref("http://150.161.93.92:8081/audiocast/rssfeed").getLink());
          atomLinks.add(new SyndicationLink().withRel("self").withType(super.getContentType()).withHref("http://150.161.93.3:9000/rssfeed").getLink());

//          if (posts.hasPrevious()) {
//              atomLinks.add(new SyndicationLink()
//                      .withRel("previous")
//                      .withType(super.getContentType())
//                      .withHref(String.format(hrefFormat, self, posts.previousPageable().getPageNumber()))
//                      .getLink()
//              );
//          }
//          if (posts.hasNext()) {
//              atomLinks.add(new SyndicationLink()
//                      .withRel("next")
//                      .withType(super.getContentType())
//                      .withHref(String.format(hrefFormat, self, posts.nextPageable().getPageNumber()))
//                      .getLink()
//              );
//          }
          feed.getModules().addAll(atomLinks.stream().map(l -> {
              final AtomLinkModuleImpl rv = new AtomLinkModuleImpl();
              rv.setLink(l);
              return rv;
          }).collect(Collectors.toList()));
          
//      
          //end atom
    
     //     modules.add(feedInfo);
        
    
	
		super.buildFeedMetadata(model, feed, request);
	}
	
	

}
