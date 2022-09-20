package geomex.xeus.ndps.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.fasterxml.jackson.core.JsonProcessingException;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import geomex.xeus.equipmgr.service.BrdcstTrmnlsService;
import geomex.xeus.smartcity.service.EventService;
import geomex.xeus.smartcity.service.EventWebSocketService;
import geomex.xeus.websocket.web.WebSocketController;
import java.time.LocalDate;


@Service("ndpsUpdateService")
public class NdpsUpdateService extends EgovAbstractServiceImpl {
    protected Logger logger = LoggerFactory.getLogger(NdpsUpdateService.class);

    @Resource(name = "propService")
    private EgovPropertyService propService;

    @Resource(name = "ndpsService")
    private NdpsService service;

    @Resource(name = "brdcstTrmnlsService")
    private BrdcstTrmnlsService brdcstTrmnlsService;

    @Resource(name = "eventService")
	private EventService event;

    @Resource(name = "eventWebSocketService")
	private EventWebSocketService socket;

    @Resource(name = "webSocketController")
	private WebSocketController webSocketController;

    @Resource(name = "txManager")
    PlatformTransactionManager transactionManager;

    private Timer reloadTimer = null;
    private TimerTask reloadTask = null;

        /**
     * 긴급구조 데이터를 업데이트한다
     * @param ndmsType
         * @throws Exception
         * @throws JsonProcessingException
     */
    public void updateNdpsDataByMysqlDb() throws Exception {

    	TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try{
			deleteBrdcstTrmnlsView();
    		insertBrdcstTrmnlsViewByMysqlDb();

    		transactionManager.commit(txStatus);
			System.out.println("[" + LocalDate.now() + "] NDPS 방송장비 업데이트 완료하였씁니다");
		}catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(txStatus);
			System.out.println("[" + LocalDate.now() + "] NDPS 방송장비 업데이트 중 오류가 발생하였습니다. 원래대로 롤백합니다.");
		}

    }

	private void deleteBrdcstTrmnlsView() throws Exception {
		boolean result = brdcstTrmnlsService.del(null);

		if(!result){
			throw new Exception();
		}

	}



    private void insertBrdcstTrmnlsViewByMysqlDb() throws Exception {

    	//mysqlDB에서 방송장비 데이터로르 가져온다
    	ArrayList<HashMap<String, String>> list = service.getAllList();

    	HashMap<String, String> map = null;

    	//예외 경위도(제주도 및 바다 어딘가)
    	String exceptionLon = "129.255764";
		String exceptionLat = "34.2825098";

    	for(int i=0; i<list.size(); i++){
    		map = list.get(i);

    		if(map.get("lo") != null && !"".equals(map.get("lo")) && map.get("la") != null && !"".equals(map.get("la"))){
				map.put("_annox", map.get("lo"));
				map.put("_annoy", map.get("la"));
				map.put("point", "POINT("+map.get("lo")+" "+ map.get("la")+")");
    		}else{
    			map.put("_annox", exceptionLon);
				map.put("_annoy", exceptionLat);
				map.put("point", "POINT("+exceptionLon+" "+ exceptionLat+")");
    		}

    		if(!brdcstTrmnlsService.add(map)){
    			throw new Exception();
    		}
    	}

	}


	/**
     * <코드>
     * B03001 -> 방송장비
     * B03101 -> 강우계
     * B03103 -> 적설계
     * B03104 -> 시정계
     * B03102 -> 수위계
     * B03105 -> aws
     *
     */

	@PostConstruct
    public void initIt() throws Exception {

        reloadTask = new TimerTask() {
            @Override
            public void run() {
                try {
                	//방송장비
                	updateNdpsDataByMysqlDb();
                } catch (Exception e) {
                    logger.error(ExceptionUtils.getStackTrace(e));
                }
            }
        };
        reloadTimer = new Timer();
        reloadTimer.schedule(reloadTask, 10 * 1000, (60 * 1000 * 60 * 24));
//        reloadTimer.schedule(reloadTask, 10 * 1000, (60 * 1000));
    }



}