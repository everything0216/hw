package summer.test.singyi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;
import summer.test.singyi.entity.Sight;
import summer.test.singyi.repository.ProductRepository;

import java.util.List;

@SpringBootApplication
public class SingyiApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SingyiApplication.class, args);
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SingyiApplication.class,args);
		applicationContext.start();
	}

	//程式開始執行
	@Component
	class ContextStartedListener implements ApplicationListener<ContextStartedEvent>{
		@Autowired	//他可以對類成員變數、方法及建構函式進行標註，完成自動裝配的工作。
		private ProductRepository repository;

		@Override
		public void onApplicationEvent(ContextStartedEvent event){
			List<Sight> sightList;
			KeelungSightsCrawler crawler = new KeelungSightsCrawler();
			sightList = crawler.getItem();
			for (Sight s : sightList){
				repository.save(s);
			}
		}
	}



}

