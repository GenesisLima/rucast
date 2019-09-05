package org.ntvru.rucast.rss;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ntvru.rucast.model.Episode;
import org.ntvru.rucast.utils.SyndicationLink;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.rometools.modules.atom.modules.AtomLinkModuleImpl;
import com.rometools.modules.itunes.EntryInformation;
import com.rometools.modules.itunes.EntryInformationImpl;
import com.rometools.modules.itunes.FeedInformation;
import com.rometools.modules.itunes.FeedInformationImpl;
import com.rometools.modules.itunes.types.Category;
import com.rometools.modules.itunes.types.Duration;
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
	 EntryInformation entryInfo = new EntryInformationImpl();
	 List<Category> categories = new ArrayList<Category>();

	 List<Item> items = new ArrayList<Item>();
	 
	 
	 public AudiocastRSSViewer() {
//		 categories.add(new Category("Education"));
		
	}
	 


	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model,
		 HttpServletRequest arg1, HttpServletResponse arg2) throws Exception {
		@SuppressWarnings("unchecked")
		List<Episode> listEps = (List<Episode>) model.get("feedEpisodes");
	
		 for(Episode episode:listEps){
		 
			 Item item = new Item();
			
			 Content content = new Content();
			 
			 content.setValue(episode.getSynopsis());
	 
			 item.setAuthor(episode.getShow().getName());
			 item.setContent(content);			
			 item.setTitle(episode.getTopic());			
			 item.setLink(episode.getFileDocument().getUri()+episode.getFileDocument().getFileName());
			 Guid guid = new Guid();
			 guid.setValue(episode.getFileDocument().getUri()+episode.getFileDocument().getFileName());
			 guid.setPermaLink(true);
			 item.setGuid(guid);
			
			 Description description = new Description();
			 description.setValue(episode.getSynopsis());
             item.setDescription(description);
             item.setPubDate(new Date());
             if(episode.getDuration()!=null) {
             String milliseconds = String.valueOf(TimeUnit.MILLISECONDS.toMinutes((long)Double.parseDouble(episode.getDuration())));             
             entryInfo.setDuration(new Duration(milliseconds));
             }
             List<org.ntvru.rucast.model.Category> catgrs = episode.getShow().getCategories();
             String[] categoryNames = new String[catgrs.size()];             
             for(int i=0; i<catgrs.size()-1; i++) {
            	 categoryNames[i] = catgrs.get(i).getName();
             }
             entryInfo.setKeywords(categoryNames);
    		 entryInfo.setSummary(description.getValue());
    		 entryInfo.setSubtitle(description.getValue());
    		 entryInfo.setAuthor(episode.getShow().getName());
    		 entryInfo.setExplicit(false);
    		 entryInfo.setBlock(false);
    		 
    		 Enclosure enclosure = new Enclosure();
         
    		enclosure.setUrl(episode.getFileDocument().getUri()+episode.getFileDocument().getFileName());
            enclosure.setType(episode.getFileDocument().getFileType());
           
            enclosure.setLength((long)Double.parseDouble(episode.getFileDocument().getFileSize()));
            item.getEnclosures().add(enclosure);
             List<Category> categories = new ArrayList<>();          
             
             item.setModules(modules);

             
           
			items.add(item);
			
			
			 
		 }		 
		 
		 
		 modules.add(entryInfo);
		
		
				 
		return items;
	}
	
	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
			HttpServletRequest request) {
		final List<Episode> listEpisodes = (List<Episode>) model.get("feedEpisodes");
		
		 FeedInformation feedInfo = new FeedInformationImpl();
         if(listEpisodes.size()>0) { 
         Episode episode = listEpisodes.get(0);
		 feed.setTitle(episode.getShow().getName());
         
		
		feed.setDescription("Este feed distribui os produtos de Rádio do Núcleo de TV e Rádio Universitária da UFPE");
		feed.setLink("http://estudante.ufpe.br/wp-content/themes/portaldoestudante/images/logo_UFPE.png");
		feed.setWebMaster("genesis.lima@ufpe.br (Genesis Lima)");
		
		feed.setEncoding("UTF-8");
		feed.setLanguage("pt-br");
		//feed.setModules(modules);
		Image ufpeImage = new Image();
		ufpeImage.setTitle(episode.getShow().getName());
		ufpeImage.setWidth(50);
		ufpeImage.setHeight(50);
		ufpeImage.setLink("https://www.ufpe.br/image/company_logo?img_id=20911&t=1542634550061");
		ufpeImage.setUrl("https://www.ufpe.br");
		feed.setImage(ufpeImage);
//		  feedInfo.setCategories(categories);
//		  feedInfo.setKeywords(new String[]{"Educação","Político"});
		  feedInfo.setOwnerName("Rádio Universitária (99.9) - UFPE");
          feedInfo.setOwnerEmailAddress("genesis.lima@ufpe.br (Genesis Lima)");
          feedInfo.setAuthor("Rádio Universitária");
          List<Category> categories = new ArrayList<>();
          Category education = new Category();
          education.setName("Education");         
          categories.add(education);
//          Category government = new Category();
//          government.setName(StringEscapeUtils.escapeXml("Government & Organizations"));     
//          government.setSubcategory(new Subcategory("National"));
//          categories.add(government);
//          Category news = new Category();
//          news.setName(StringEscapeUtils.escapeXml("News & Politics"));         
//          categories.add(news);
          
          
         }

          feedInfo.setCategories(categories);
          feedInfo.setSummary("Este feed distribui os produtos de áudio do Núcleo de TV e Rádio Universitária da UFPE.");
          feed.getModules().add(feedInfo);
         
          //begin atom
          final List<Link> atomLinks = new ArrayList<>();
          atomLinks.add(new SyndicationLink().withRel("self").withType(super.getContentType()).withHref("http://150.161.93.221/rssfeed.xml").getLink());


          feed.getModules().addAll(atomLinks.stream().map(l -> {
              final AtomLinkModuleImpl rv = new AtomLinkModuleImpl();
              rv.setLink(l);
              return rv;
          }).collect(Collectors.toList()));
          
          //end atom
    
        
    
	
		super.buildFeedMetadata(model, feed, request);
	}
	
	

}
