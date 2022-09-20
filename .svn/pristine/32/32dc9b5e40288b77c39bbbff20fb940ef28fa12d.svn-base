package geomex.xeus.eventmonitor.web;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import geomex.xeus.eventmonitor.service.EliFoaForestMapService;
import geomex.xeus.eventmonitor.service.EliFoaForestMapVo;
import geomex.xeus.eventmonitor.service.EliHrfRfhrService;
import geomex.xeus.eventmonitor.service.EliHrfRfhrVo;
import geomex.xeus.eventmonitor.service.EliKhcAccService;
import geomex.xeus.eventmonitor.service.EliKhcAccVo;
import geomex.xeus.eventmonitor.service.EliKmaAws10mService;
import geomex.xeus.eventmonitor.service.EliKmaAws10mVo;
import geomex.xeus.eventmonitor.service.EliKmaDfsShrtService;
import geomex.xeus.eventmonitor.service.EliKmaDfsShrtVo;
import geomex.xeus.eventmonitor.service.EliKmaEarthInfmService;
import geomex.xeus.eventmonitor.service.EliKmaEarthInfmVo;
import geomex.xeus.eventmonitor.service.EliKmaInformService;
import geomex.xeus.eventmonitor.service.EliKmaInformVo;
import geomex.xeus.eventmonitor.service.EliLayerService;
import geomex.xeus.eventmonitor.service.EliLogStatService;
import geomex.xeus.eventmonitor.service.EliNemEmreService;
import geomex.xeus.eventmonitor.service.EliNemEmreVo;
import geomex.xeus.eventmonitor.service.EliNemFirsService;
import geomex.xeus.eventmonitor.service.EliNemFirsVo;
import geomex.xeus.eventmonitor.service.EliRtsaOccuridService;
import geomex.xeus.eventmonitor.service.EliRtsaOccuridVo;
import geomex.xeus.log.service.IfDscLogService;
import geomex.xeus.map.service.GeometryService;
import geomex.xeus.map.service.SearchService;
import geomex.xeus.sysmgr.service.OrganizationService;
import geomex.xeus.sysmgr.web.CodeCtrl;
import geomex.xeus.sysmgr.web.ColumnController;
import geomex.xeus.system.annotation.NoSession;
import geomex.xeus.tvius.service.CrmsSysParamService;
import geomex.xeus.user.service.UserService;
import geomex.xeus.util.code.CodeConvertor;
import geomex.xeus.util.code.DateUtil;
import geomex.xeus.util.code.SystemParameter;
import geomex.xeus.util.code.ValidInspector;
import net.sf.json.util.JSONBuilder;

/**
 * <pre>
 * 파일명 :  EventController.java
 * 설  명 :
 *   스마트 플랫폼 5대 연계 서비스 연동 관련 컨트롤러
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2018-04-13      이은규          최초 생성
 *
 * </pre>
 *
 * @since   :  2018. 04. 13.
 * @version :  1.0
 * @see
 */
@Controller
@RequestMapping("/event")
public class EventController {

	@Resource(name = "codeCtrl")
	private CodeCtrl code;

	@Resource(name = "eliFoaForestMapService")
    private EliFoaForestMapService eliFoaForestMap;

	@Resource(name = "eliHrfRfhrService")
    private EliHrfRfhrService eliHrfRfhr;

	@Resource(name = "eliKhcAccService")
    private EliKhcAccService eliKhcAcc;

	@Resource(name = "eliKmaAws10mService")
    private EliKmaAws10mService eliKmaAws10m;

	@Resource(name = "eliKmaDfsShrtService")
    private EliKmaDfsShrtService eliKmaDfsShrt;

	@Resource(name = "eliKmaEarthInfmService")
    private EliKmaEarthInfmService eliKmaEarthInfm;

	@Resource(name = "eliKmaInformService")
    private EliKmaInformService eliKmaInform;

	@Resource(name = "eliNemEmreService")
    private EliNemEmreService eliNemEmre;

	@Resource(name = "eliNemFirsService")
    private EliNemFirsService eliNemFirs;

	@Resource(name = "eliRtsaOccuridService")
    private EliRtsaOccuridService eliRtsaOccurid;

	@Resource(name = "eliLayerService")
    private EliLayerService eliLayer;

	@Resource(name = "eliLogStatService")
    private EliLogStatService eliLogStat;


	@Resource(name = "ifDscLogService")
    private IfDscLogService ifDscLog;

	@Resource(name = "userService")
    private UserService userService;

	@Resource(name = "geometryService")
	private GeometryService geom;

	@Resource(name = "searchService")
	private SearchService bjd;

	@Resource(name = "crmsSysParamService")
    private CrmsSysParamService param;

	@Resource(name = "organizationService")
	private OrganizationService orgz;

	@Resource
	private ColumnController col;

	@Resource(name = "txManager")
    PlatformTransactionManager transactionManager;

	@Resource
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(this.validator);
		/*binder.registerCustomEditor(MultipartFile.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				//System.out.println("initBinder MultipartFile.class: {}; set null; " + text);
				setValue(null);
			}
		});*/
	}

	/*
	 *
	 * 산불발생정보
	 *
	 */

	/**
     * 산불발생정보 리스트를 조회합니다.
     *
     * @param model
     * @param map
     * @throws Exception
     */
	@NoSession
    @RequestMapping(value = "/getEliFoaForestMapList.json", method = RequestMethod.POST)
    public void getEliFoaForestMapList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("count", eliFoaForestMap.getCount(map));
        model.addAttribute("result", eliFoaForestMap.getList(map));

    }

    /**
     * 산불발생정보 단건을 조회합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/getEliFoaForestMapItem.json", method = RequestMethod.POST)
    public void getEliFoaForestMapItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        //model.addAttribute("column", eliFoaForestMap.getColList(null));
        model.addAttribute("result", eliFoaForestMap.getItem(map));

    }

    /**
     * 산불발생정보 단건을 삭제합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/delEliFoaForestMap.json", method = RequestMethod.POST)
    public void delEliFoaForestMap(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", eliFoaForestMap.del(map));
    }

	/**
     * 산불발생정보를 추가합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/addEliFoaForestMap.json", method = RequestMethod.POST)
    public void addEliFoaForestMap(Model model, HttpSession session, @ModelAttribute @Valid EliFoaForestMapVo vo, BindingResult br) throws Exception {

        String[] ignoreField = {""};
        String msg = ValidInspector.findError(br, ignoreField);

        if("pass".equals(msg)){
            model.addAttribute("eliFoaForestMapVo", null);
            model.addAttribute("result", eliFoaForestMap.add(vo));
        }else{
            model.addAttribute("error", msg);
        }

    }

    /**
     * 산불발생정보 내용을 수정합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/editEliFoaForestMap.json", method = RequestMethod.POST)
    public void editEliFoaForestMap(Model model, HttpSession session, @ModelAttribute @Valid EliFoaForestMapVo vo, BindingResult br) throws Exception {

        String[] ignoreField = {""};
        String msg = ValidInspector.findError(br, ignoreField);

        if("pass".equals(msg)){
            model.addAttribute("eliFoaForestMapVo", null);
            model.addAttribute("result", eliFoaForestMap.edit(vo));
        }else{
            model.addAttribute("error", msg);
        }

    }


    /*
     *
     * 우량시단위
     *
     */

    /**
     * 우량시단위 리스트를 조회합니다.
     *
     * @param model
     * @param map
     * @throws Exception
     */
    @RequestMapping(value = "/getEliHrfRfhrList.json", method = RequestMethod.POST)
    public void getEliHrfRfhrList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("count", eliHrfRfhr.getCount(map));
        model.addAttribute("result", eliHrfRfhr.getList(map));

    }

    /**
     * 우량시단위 단건을 조회합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/getEliHrfRfhrItem.json", method = RequestMethod.POST)
    public void getEliHrfRfhrItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        //model.addAttribute("column", eliHrfRfhr.getColList(null));
        model.addAttribute("result", eliHrfRfhr.getItem(map));

    }

    /**
     * 우량시단위 단건을 삭제합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/delEliHrfRfhr.json", method = RequestMethod.POST)
    public void delEliHrfRfhr(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", eliHrfRfhr.del(map));
    }

    /**
     * 우량시단위를 추가합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/addEliHrfRfhr.json", method = RequestMethod.POST)
    public void addEliHrfRfhr(Model model, HttpSession session, @ModelAttribute @Valid EliHrfRfhrVo vo, BindingResult br) throws Exception {

        String[] ignoreField = {""};
        String msg = ValidInspector.findError(br, ignoreField);

        if("pass".equals(msg)){
            model.addAttribute("eliHrfRfhrVo", null);
            model.addAttribute("result", eliHrfRfhr.add(vo));
        }else{
            model.addAttribute("error", msg);
        }

    }

    /**
     * 우량시단위 내용을 수정합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/editEliHrfRfhr.json", method = RequestMethod.POST)
    public void editEliHrfRfhr(Model model, HttpSession session, @ModelAttribute @Valid EliHrfRfhrVo vo, BindingResult br) throws Exception {

        String[] ignoreField = {""};
        String msg = ValidInspector.findError(br, ignoreField);

        if("pass".equals(msg)){
            model.addAttribute("eliHrfRfhrVo", null);
            model.addAttribute("result", eliHrfRfhr.edit(vo));
        }else{
            model.addAttribute("error", msg);
        }

    }


    /*
    *
    * 고속도로특별상황관리
    *
    */

   /**
    * 고속도로특별상황관리 리스트를 조회합니다.
    *
    * @param model
    * @param map
    * @throws Exception
    */
   @RequestMapping(value = "/getEliKhcAccList.json", method = RequestMethod.POST)
   public void getEliKhcAccList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

       model.addAttribute("count", eliKhcAcc.getCount(map));
       model.addAttribute("result", eliKhcAcc.getList(map));

   }

   /**
    * 고속도로특별상황관리 단건을 조회합니다.
    *
    * @param model
    * @param session
    * @param vo
    * @throws Exception
    */
   @RequestMapping(value = "/getEliKhcAccItem.json", method = RequestMethod.POST)
   public void getEliKhcAccItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

       //model.addAttribute("column", eliKhcAcc.getColList(null));
       model.addAttribute("result", eliKhcAcc.getItem(map));

   }

   /**
    * 고속도로특별상황관리 단건을 삭제합니다.
    *
    * @param model
    * @param session
    * @param vo
    * @throws Exception
    */
   @RequestMapping(value = "/delEliKhcAcc.json", method = RequestMethod.POST)
   public void delEliKhcAcc(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

       model.addAttribute("result", eliKhcAcc.del(map));
   }

   /**
    * 고속도로특별상황관리를 추가합니다.
    *
    * @param model
    * @param session
    * @param vo
    * @throws Exception
    */
   @RequestMapping(value = "/addEliKhcAcc.json", method = RequestMethod.POST)
   public void addEliKhcAcc(Model model, HttpSession session, @ModelAttribute @Valid EliKhcAccVo vo, BindingResult br) throws Exception {

       String[] ignoreField = {""};
       String msg = ValidInspector.findError(br, ignoreField);

       if("pass".equals(msg)){
           model.addAttribute("eliKhcAccVo", null);
           model.addAttribute("result", eliKhcAcc.add(vo));
       }else{
           model.addAttribute("error", msg);
       }

   }

   /**
    * 고속도로특별상황관리 내용을 수정합니다.
    *
    * @param model
    * @param session
    * @param vo
    * @throws Exception
    */
   @RequestMapping(value = "/editEliKhcAcc.json", method = RequestMethod.POST)
   public void editEliKhcAcc(Model model, HttpSession session, @ModelAttribute @Valid EliKhcAccVo vo, BindingResult br) throws Exception {

       String[] ignoreField = {""};
       String msg = ValidInspector.findError(br, ignoreField);

       if("pass".equals(msg)){
           model.addAttribute("eliKhcAccVo", null);
           model.addAttribute("result", eliKhcAcc.edit(vo));
       }else{
           model.addAttribute("error", msg);
       }

   }


   /*
   *
   * 기상정보AWS
   *
   */

  /**
   * 기상정보AWS 리스트를 조회합니다.
   *
   * @param model
   * @param map
   * @throws Exception
   */
  @RequestMapping(value = "/getEliKmaAws10mList.json", method = RequestMethod.POST)
  public void getEliKmaAws10mList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

      model.addAttribute("count", eliKmaAws10m.getCount(map));
      model.addAttribute("result", eliKmaAws10m.getList(map));

  }

  /**
   * 기상정보AWS 단건을 조회합니다.
   *
   * @param model
   * @param session
   * @param vo
   * @throws Exception
   */
  @RequestMapping(value = "/getEliKmaAws10mItem.json", method = RequestMethod.POST)
  public void getEliKmaAws10mItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

      //model.addAttribute("column", eliKmaAws10m.getColList(null));
      model.addAttribute("result", eliKmaAws10m.getItem(map));

  }

  /**
   * 기상정보AWS 단건을 삭제합니다.
   *
   * @param model
   * @param session
   * @param vo
   * @throws Exception
   */
  @RequestMapping(value = "/delEliKmaAws10m.json", method = RequestMethod.POST)
  public void delEliKmaAws10m(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

      model.addAttribute("result", eliKmaAws10m.del(map));
  }

  /**
   * 기상정보AWS를 추가합니다.
   *
   * @param model
   * @param session
   * @param vo
   * @throws Exception
   */
  @RequestMapping(value = "/addEliKmaAws10m.json", method = RequestMethod.POST)
  public void addEliKmaAws10m(Model model, HttpSession session, @ModelAttribute @Valid EliKmaAws10mVo vo, BindingResult br) throws Exception {

      String[] ignoreField = {""};
      String msg = ValidInspector.findError(br, ignoreField);

      if("pass".equals(msg)){
          model.addAttribute("eliKmaAws10mVo", null);
          model.addAttribute("result", eliKmaAws10m.add(vo));
      }else{
          model.addAttribute("error", msg);
      }

  }

  /**
   * 기상정보AWS 내용을 수정합니다.
   *
   * @param model
   * @param session
   * @param vo
   * @throws Exception
   */
  @RequestMapping(value = "/editEliKmaAws10m.json", method = RequestMethod.POST)
  public void editEliKmaAws10m(Model model, HttpSession session, @ModelAttribute @Valid EliKmaAws10mVo vo, BindingResult br) throws Exception {

      String[] ignoreField = {""};
      String msg = ValidInspector.findError(br, ignoreField);

      if("pass".equals(msg)){
          model.addAttribute("eliKmaAws10mVo", null);
          model.addAttribute("result", eliKmaAws10m.edit(vo));
      }else{
          model.addAttribute("error", msg);
      }

  }



  /*
  *
  * 동네예보
  *
  */

 /**
  * 동네예보 리스트를 조회합니다.
  *
  * @param model
  * @param map
  * @throws Exception
  */
 @RequestMapping(value = "/getEliKmaDfsShrtList.json", method = RequestMethod.POST)
 public void getEliKmaDfsShrtList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

     model.addAttribute("count", eliKmaDfsShrt.getCount(map));
     model.addAttribute("result", eliKmaDfsShrt.getList(map));

 }

 /**
  * 동네예보 단건을 조회합니다.
  *
  * @param model
  * @param session
  * @param vo
  * @throws Exception
  */
 @RequestMapping(value = "/getEliKmaDfsShrtItem.json", method = RequestMethod.POST)
 public void getEliKmaDfsShrtItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

     //model.addAttribute("column", eliKmaDfsShrt.getColList(null));
     model.addAttribute("result", eliKmaDfsShrt.getItem(map));

 }

 /**
  * 동네예보 단건을 삭제합니다.
  *
  * @param model
  * @param session
  * @param vo
  * @throws Exception
  */
 @RequestMapping(value = "/delEliKmaDfsShrt.json", method = RequestMethod.POST)
 public void delEliKmaDfsShrt(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

     model.addAttribute("result", eliKmaDfsShrt.del(map));
 }

 /**
  * 지진현황를 추가합니다.
  *
  * @param model
  * @param session
  * @param vo
  * @throws Exception
  */
 @RequestMapping(value = "/addEliKmaDfsShrt.json", method = RequestMethod.POST)
 public void addEliKmaDfsShrt(Model model, HttpSession session, @ModelAttribute @Valid EliKmaDfsShrtVo vo, BindingResult br) throws Exception {

     String[] ignoreField = {""};
     String msg = ValidInspector.findError(br, ignoreField);

     if("pass".equals(msg)){
         model.addAttribute("eliKmaDfsShrtVo", null);
         model.addAttribute("result", eliKmaDfsShrt.add(vo));
     }else{
         model.addAttribute("error", msg);
     }

 }

 /**
  * 동네예보 내용을 수정합니다.
  *
  * @param model
  * @param session
  * @param vo
  * @throws Exception
  */
 @RequestMapping(value = "/editEliKmaDfsShrt.json", method = RequestMethod.POST)
 public void editEliKmaDfsShrt(Model model, HttpSession session, @ModelAttribute @Valid EliKmaDfsShrtVo vo, BindingResult br) throws Exception {

     String[] ignoreField = {""};
     String msg = ValidInspector.findError(br, ignoreField);

     if("pass".equals(msg)){
         model.addAttribute("eliKmaDfsShrtVo", null);
         model.addAttribute("result", eliKmaDfsShrt.edit(vo));
     }else{
         model.addAttribute("error", msg);
     }

 }


     /*
     *
     * 지진현황
     *
     */

    /**
     * 지진현황 리스트를 조회합니다.
     *
     * @param model
     * @param map
     * @throws Exception
     */
    @RequestMapping(value = "/getEliKmaEarthInfmList.json", method = RequestMethod.POST)
    public void getEliKmaEarthInfmList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("count", eliKmaEarthInfm.getCount(map));
        model.addAttribute("result", eliKmaEarthInfm.getList(map));

    }

    /**
     * 지진현황 단건을 조회합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/getEliKmaEarthInfmItem.json", method = RequestMethod.POST)
    public void getEliKmaEarthInfmItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        //model.addAttribute("column", eliKmaEarthInfm.getColList(null));
        model.addAttribute("result", eliKmaEarthInfm.getItem(map));

    }

    /**
     * 지진현황 단건을 삭제합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/delEliKmaEarthInfm.json", method = RequestMethod.POST)
    public void delEliKmaEarthInfm(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", eliKmaEarthInfm.del(map));
    }

    /**
     * 지진현황를 추가합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/addEliKmaEarthInfm.json", method = RequestMethod.POST)
    public void addEliKmaEarthInfm(Model model, HttpSession session, @ModelAttribute @Valid EliKmaEarthInfmVo vo, BindingResult br) throws Exception {

        String[] ignoreField = {""};
        String msg = ValidInspector.findError(br, ignoreField);

        if("pass".equals(msg)){
            model.addAttribute("eliKmaEarthInfmVo", null);
            model.addAttribute("result", eliKmaEarthInfm.add(vo));
        }else{
            model.addAttribute("error", msg);
        }

    }

    /**
     * 지진현황 내용을 수정합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/editEliKmaEarthInfm.json", method = RequestMethod.POST)
    public void editEliKmaEarthInfm(Model model, HttpSession session, @ModelAttribute @Valid EliKmaEarthInfmVo vo, BindingResult br) throws Exception {

        String[] ignoreField = {""};
        String msg = ValidInspector.findError(br, ignoreField);

        if("pass".equals(msg)){
            model.addAttribute("eliKmaEarthInfmVo", null);
            model.addAttribute("result", eliKmaEarthInfm.edit(vo));
        }else{
            model.addAttribute("error", msg);
        }

    }



    /*
    *
    * 기상특보
    *
    */

   /**
    * 기상특보 리스트를 조회합니다.
    *
    * @param model
    * @param map
    * @throws Exception
    */
   @RequestMapping(value = "/getEliKmaInformList.json", method = RequestMethod.POST)
   public void getEliKmaInformList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

       model.addAttribute("count", eliKmaInform.getCount(map));
       model.addAttribute("result", eliKmaInform.getList(map));

   }

   /**
    * 기상특보 단건을 조회합니다.
    *
    * @param model
    * @param session
    * @param vo
    * @throws Exception
    */
   @RequestMapping(value = "/getEliKmaInformItem.json", method = RequestMethod.POST)
   public void getEliKmaInformItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

       //model.addAttribute("column", eliKmaInform.getColList(null));
       model.addAttribute("result", eliKmaInform.getItem(map));

   }

   /**
    * 기상특보 단건을 삭제합니다.
    *
    * @param model
    * @param session
    * @param vo
    * @throws Exception
    */
   @RequestMapping(value = "/delEliKmaInform.json", method = RequestMethod.POST)
   public void delEliKmaInform(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

       model.addAttribute("result", eliKmaInform.del(map));
   }

   /**
    * 기상특보를 추가합니다.
    *
    * @param model
    * @param session
    * @param vo
    * @throws Exception
    */
   @RequestMapping(value = "/addEliKmaInform.json", method = RequestMethod.POST)
   public void addEliKmaInform(Model model, HttpSession session, @ModelAttribute @Valid EliKmaInformVo vo, BindingResult br) throws Exception {

       String[] ignoreField = {""};
       String msg = ValidInspector.findError(br, ignoreField);

       if("pass".equals(msg)){
           model.addAttribute("eliKmaInformVo", null);
           model.addAttribute("result", eliKmaInform.add(vo));
       }else{
           model.addAttribute("error", msg);
       }

   }

   /**
    * 기상특보 내용을 수정합니다.
    *
    * @param model
    * @param session
    * @param vo
    * @throws Exception
    */
   @RequestMapping(value = "/editEliKmaInform.json", method = RequestMethod.POST)
   public void editEliKmaInform(Model model, HttpSession session, @ModelAttribute @Valid EliKmaInformVo vo, BindingResult br) throws Exception {

       String[] ignoreField = {""};
       String msg = ValidInspector.findError(br, ignoreField);

       if("pass".equals(msg)){
           model.addAttribute("eliKmaInformVo", null);
           model.addAttribute("result", eliKmaInform.edit(vo));
       }else{
           model.addAttribute("error", msg);
       }

   }



   /*
   *
   * 응급복구장비
   *
   */

  /**
   * 응급복구장비 리스트를 조회합니다.
   *
   * @param model
   * @param map
   * @throws Exception
   */
  @RequestMapping(value = "/getEliNemEmreList.json", method = RequestMethod.POST)
  public void getEliNemEmreList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

      model.addAttribute("count", eliNemEmre.getCount(map));
      model.addAttribute("result", eliNemEmre.getList(map));

  }

  /**
   * 응급복구장비 단건을 조회합니다.
   *
   * @param model
   * @param session
   * @param vo
   * @throws Exception
   */
  @RequestMapping(value = "/getEliNemEmreItem.json", method = RequestMethod.POST)
  public void getEliNemEmreItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

      //model.addAttribute("column", eliNemEmre.getColList(null));
      model.addAttribute("result", eliNemEmre.getItem(map));

  }

  /**
   * 응급복구장비 단건을 삭제합니다.
   *
   * @param model
   * @param session
   * @param vo
   * @throws Exception
   */
  @RequestMapping(value = "/delEliNemEmre.json", method = RequestMethod.POST)
  public void delEliNemEmre(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

      model.addAttribute("result", eliNemEmre.del(map));
  }

  /**
   * 응급복구장비를 추가합니다.
   *
   * @param model
   * @param session
   * @param vo
   * @throws Exception
   */
  @RequestMapping(value = "/addEliNemEmre.json", method = RequestMethod.POST)
  public void addEliNemEmre(Model model, HttpSession session, @ModelAttribute @Valid EliNemEmreVo vo, BindingResult br) throws Exception {

      String[] ignoreField = {""};
      String msg = ValidInspector.findError(br, ignoreField);

      if("pass".equals(msg)){
          model.addAttribute("eliNemEmreVo", null);
          model.addAttribute("result", eliNemEmre.add(vo));
      }else{
          model.addAttribute("error", msg);
      }

  }

  /**
   * 응급복구장비 내용을 수정합니다.
   *
   * @param model
   * @param session
   * @param vo
   * @throws Exception
   */
  @RequestMapping(value = "/editEliNemEmre.json", method = RequestMethod.POST)
  public void editEliNemEmre(Model model, HttpSession session, @ModelAttribute @Valid EliNemEmreVo vo, BindingResult br) throws Exception {

      String[] ignoreField = {""};
      String msg = ValidInspector.findError(br, ignoreField);

      if("pass".equals(msg)){
          model.addAttribute("eliNemEmreVo", null);
          model.addAttribute("result", eliNemEmre.edit(vo));
      }else{
          model.addAttribute("error", msg);
      }

  }



      /*
      *
      * 소방서
      *
      */

     /**
      * 소방서 리스트를 조회합니다.
      *
      * @param model
      * @param map
      * @throws Exception
      */
     @RequestMapping(value = "/getEliNemFirsList.json", method = RequestMethod.POST)
     public void getEliNemFirsList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

         model.addAttribute("count", eliNemFirs.getCount(map));
         model.addAttribute("result", eliNemFirs.getList(map));

     }

     /**
      * 소방서 단건을 조회합니다.
      *
      * @param model
      * @param session
      * @param vo
      * @throws Exception
      */
     @RequestMapping(value = "/getEliNemFirsItem.json", method = RequestMethod.POST)
     public void getEliNemFirsItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

         //model.addAttribute("column", eliNemFirs.getColList(null));
         model.addAttribute("result", eliNemFirs.getItem(map));

     }

     /**
      * 소방서 단건을 삭제합니다.
      *
      * @param model
      * @param session
      * @param vo
      * @throws Exception
      */
     @RequestMapping(value = "/delEliNemFirs.json", method = RequestMethod.POST)
     public void delEliNemFirs(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

         model.addAttribute("result", eliNemFirs.del(map));
     }

     /**
      * 소방서를 추가합니다.
      *
      * @param model
      * @param session
      * @param vo
      * @throws Exception
      */
     @RequestMapping(value = "/addEliNemFirs.json", method = RequestMethod.POST)
     public void addEliNemFirs(Model model, HttpSession session, @ModelAttribute @Valid EliNemFirsVo vo, BindingResult br) throws Exception {

         String[] ignoreField = {""};
         String msg = ValidInspector.findError(br, ignoreField);

         if("pass".equals(msg)){
             model.addAttribute("eliNemFirsVo", null);
             model.addAttribute("result", eliNemFirs.add(vo));
         }else{
             model.addAttribute("error", msg);
         }

     }

     /**
      * 소방서 내용을 수정합니다.
      *
      * @param model
      * @param session
      * @param vo
      * @throws Exception
      */
     @RequestMapping(value = "/editEliNemFirs.json", method = RequestMethod.POST)
     public void editEliNemFirs(Model model, HttpSession session, @ModelAttribute @Valid EliNemFirsVo vo, BindingResult br) throws Exception {

         String[] ignoreField = {""};
         String msg = ValidInspector.findError(br, ignoreField);

         if("pass".equals(msg)){
             model.addAttribute("eliNemFirsVo", null);
             model.addAttribute("result", eliNemFirs.edit(vo));
         }else{
             model.addAttribute("error", msg);
         }

     }



     /*
     *
     * 시가지도로돌발상황정보
     *
     */

    /**
     * 시가지도로돌발상황정보 리스트를 조회합니다.
     *
     * @param model
     * @param map
     * @throws Exception
     */
    @RequestMapping(value = "/getEliRtsaOccuridList.json", method = RequestMethod.POST)
    public void getEliRtsaOccuridList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("count", eliRtsaOccurid.getCount(map));
        model.addAttribute("result", eliRtsaOccurid.getList(map));

    }

    /**
     * 시가지도로돌발상황정보 단건을 조회합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/getEliRtsaOccuridItem.json", method = RequestMethod.POST)
    public void getEliRtsaOccuridItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        //model.addAttribute("column", eliRtsaOccurid.getColList(null));
        model.addAttribute("result", eliRtsaOccurid.getItem(map));

    }

    /**
     * 시가지도로돌발상황정보 단건을 삭제합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/delEliRtsaOccurid.json", method = RequestMethod.POST)
    public void delEliRtsaOccurid(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", eliRtsaOccurid.del(map));
    }

    /**
     * 시가지도로돌발상황정보를 추가합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/addEliRtsaOccurid.json", method = RequestMethod.POST)
    public void addEliRtsaOccurid(Model model, HttpSession session, @ModelAttribute @Valid EliRtsaOccuridVo vo, BindingResult br) throws Exception {

        String[] ignoreField = {""};
        String msg = ValidInspector.findError(br, ignoreField);

        if("pass".equals(msg)){
            model.addAttribute("eliRtsaOccuridVo", null);
            model.addAttribute("result", eliRtsaOccurid.add(vo));
        }else{
            model.addAttribute("error", msg);
        }

    }

    /**
     * 시가지도로돌발상황정보 내용을 수정합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/editEliRtsaOccurid.json", method = RequestMethod.POST)
    public void editEliRtsaOccurid(Model model, HttpSession session, @ModelAttribute @Valid EliRtsaOccuridVo vo, BindingResult br) throws Exception {

        String[] ignoreField = {""};
        String msg = ValidInspector.findError(br, ignoreField);

        if("pass".equals(msg)){
            model.addAttribute("eliRtsaOccuridVo", null);
            model.addAttribute("result", eliRtsaOccurid.edit(vo));
        }else{
            model.addAttribute("error", msg);
        }

    }

      /**
       * 긴급재난상황 메인 뷰를 리턴합니다.
       *
       * @param model
       * @return
       * @throws Exception
       */
      @RequestMapping(value = "/getDisasterView.do")
      public String getDisasterView(Model model) throws Exception {

          return "/eventMonitor/disasterView";
      }


      /**
       * 112 긴급출동 사건접수 첨부파일을 추가합니다.
       *
       * @param model
       * @param param
       * @param br
       * @return
       * @throws Exception
       */
      @RequestMapping(value = "/addAtchFile.json", method = RequestMethod.POST)
      public void addAtchFile(Model model, HttpSession session, @RequestParam(value="p", required=true) String sub,
                                                               @RequestParam(value="uploadImg", required=true) MultipartFile file) throws Exception {
          /**
           * 시스템 파라미터에서 영상 업로드 경로를 가져온다.
           */
          String upPath = "";

          HashMap<String, String> map = null;

          SystemParameter sysParam = new SystemParameter(param.getList(null));
          map = sysParam.getParamMap();
          upPath = map.get("sys.upload_path");

          if(file.isEmpty()){
              model.addAttribute("error", "파일이 선택되지 않았습니다.");
          }else{
              String [] splitFileNm = file.getOriginalFilename().split("\\.");
              String extension = "." + splitFileNm[splitFileNm.length-1];

              String realFileNm = DateUtil.getStrMilSec() + "-" + file.getOriginalFilename();
              String path = upPath + sub;

              System.out.println(path);
              if("jpeg".equals(extension)) extension = "jpg";

              System.out.println( "현재 확장자 : " + extension);

              //if(ValidInspector.isDataExtension(type)){
              if(ValidInspector.isImageExtension(extension) || ValidInspector.isVideoExtension(extension) || ValidInspector.isCompExtension(extension)){

                  File pathDir = new File(path);
                  if(!pathDir.exists()) pathDir.mkdirs();

                  System.out.println(path + realFileNm);
                  File img = new File(path + realFileNm);
                  file.transferTo(img);

                  String extTyp = "0";
                  if(extension.equals("avi") || extension.equals("mkv") || extension.equals("mp4") || extension.equals("wmv")) extTyp = "1";
                  model.addAttribute("extension", extTyp);
                  model.addAttribute("uploadNm", file.getOriginalFilename());
                  model.addAttribute("realNm", realFileNm);
              }else{
                  model.addAttribute("error", "파일은 gif, jpg, jpeg, png, avi, mkv, mp4, wmv, alz, gz, rar, tar, tgz, z, zip, 7z 파일만 업로드 할 수 있습니다.");
              }
          }

      }

      /**
       * 112 긴급출동 사건접수 추가첨부파일을 추가합니다.
       *
       * @param model
       * @param param
       * @param br
       * @return
       * @throws Exception
       */
      @RequestMapping(value = "/addAtchFile2.json", method = RequestMethod.POST)
      public void addAtchFile2(Model model, HttpSession session, @RequestParam(value="p2", required=true) String sub,
                                                               @RequestParam(value="uploadImg2", required=true) MultipartFile file) throws Exception {
          /**
           * 시스템 파라미터에서 영상 업로드 경로를 가져온다.
           */
          String upPath = "";

          HashMap<String, String> map = null;

          SystemParameter sysParam = new SystemParameter(param.getList(null));
          map = sysParam.getParamMap();
          upPath = map.get("sys.upload_path");

          if(file.isEmpty()){
              model.addAttribute("error", "파일이 선택되지 않았습니다.");
          }else{
              String [] splitFileNm = file.getOriginalFilename().split("\\.");
              String extension = "." + splitFileNm[splitFileNm.length-1];

              String realFileNm = DateUtil.getStrMilSec() + "-" + file.getOriginalFilename();
              String path = upPath + sub;

              System.out.println(path);
              if("jpeg".equals(extension)) extension = "jpg";

              System.out.println( "현재 확장자 : " + extension);

              //if(ValidInspector.isDataExtension(type)){
              if(ValidInspector.isImageExtension(extension) || ValidInspector.isVideoExtension(extension) || ValidInspector.isCompExtension(extension)){

                  File pathDir = new File(path);
                  if(!pathDir.exists()) pathDir.mkdirs();

                  System.out.println(path + realFileNm);
                  File img = new File(path + realFileNm);
                  file.transferTo(img);

                  String extTyp = "0";
                  if(extension.equals("avi") || extension.equals("mkv") || extension.equals("mp4") || extension.equals("wmv")) extTyp = "1";
                  else if (extension.equals("alz") || extension.equals("gz") || extension.equals("rar") ||
                          extension.equals("tar") || extension.equals("tgz") || extension.equals("z") ||
                          extension.equals("tgz")|| extension.equals("zip")|| extension.equals("7z")) extTyp = "2";
                  model.addAttribute("extension", extTyp);
                  model.addAttribute("uploadNm", file.getOriginalFilename());
                  model.addAttribute("realNm", realFileNm);
              }else{
                  model.addAttribute("error", "파일은 gif, jpg, jpeg, png, avi, mkv, mp4, wmv, alz, gz, rar, tar, tgz, z, zip, 7z 파일만 업로드 할 수 있습니다.");
              }
          }

      }

      /**
       * 이벤트(5대연계서비스) 전용 심볼 목록을 리턴합니다.
       *
       * @param dirPath - 심볼경로
       * @return
       */
      private ArrayList<String> getSymbolList(String dirPath) {
          ArrayList<String> symList = new ArrayList<String>();

          File dir = new File(dirPath);
          if(dir.isDirectory()){
              File[] files = dir.listFiles();
              for(int i=0; i<files.length; i++){
                  symList.add(files[i].getName());
              }
          }

          return symList;
      }

      /**
       * 이벤트(5대연계서비스) 주제도 목록을 리턴합니다.
       *
       * @param model
       * @param session
       * @param map
       * @throws Exception
       */
      @RequestMapping(value = "getEvtTheme.json", method = RequestMethod.POST)
      public void getEvtTheme(Model model, HttpSession session) throws Exception {

          ArrayList<String> symList = getSymbolList(session.getServletContext().getRealPath("/resources/sym/evt/"));

          HashMap<String, String> theme = new HashMap<String, String>();
          for(int i=11; i<=20; i++){
              for(int l=0; l<symList.size(); l++){
                  if(Integer.toString(i).equals(symList.get(l).replace(".png", ""))){
                      theme.put(Integer.toString(i), symList.get(l));
                      break;
                  }
              }
          }

          model.addAttribute("result", theme);

      }

      /**
       * 관공서 주제도 목록을 리턴합니다.
       *
       * @param model
       * @param session
       * @param map
       * @throws Exception
       */
      @RequestMapping(value = "getGovTheme.json", method = RequestMethod.POST)
      public void getGovTheme(Model model, HttpSession session) throws Exception {

          CodeConvertor cde = new CodeConvertor(code.getCdeList());
          HashMap<String, String> gov = cde.convertCodeGrpToAllCde("C70");
          ArrayList<String> symList = getSymbolList(session.getServletContext().getRealPath("/resources/sym/gov/"));

          HashMap<String, String> theme = new HashMap<String, String>();
          for(int i=11; i<=20; i++){
              for(int l=0; l<symList.size(); l++){
                  if(Integer.toString(i).equals(symList.get(l).replace(".png", ""))){
                      theme.put(Integer.toString(i), symList.get(l));
                      break;
                  }
              }
          }

          model.addAttribute("result", theme);

      }

      /**
       * 레어어용 이벤트 리스트를 리턴합니다.
       *
       * @param kvp
       * @param res
       * @return
       */
      @RequestMapping(value = "eventmap")
      public @ResponseBody String getEventMap(HttpServletResponse res, @RequestParam HashMap<String, String> kvp) {

          try {
              res.setContentType("application/json; charset=utf-8");
              res.setHeader("Cache-Control", "no-cache");
              res.setDateHeader("Expires", 1);
              res.setCharacterEncoding("UTF-8");

              /*CctvSymCmd cmd = new CctvSymCmd();
              cmd.parseKvp(kvp);
              return cctvMapService.getSymbolGroupAsJSON(cmd);*/

              /*for( String key : kvp.keySet() ){
                  System.out.println( String.format("키 : %s, 값 : %s", key, kvp.get(key)) );
              }*/

              JSONParser parser = new JSONParser();
              JSONArray jsonArr = (JSONArray)parser.parse(kvp.get("list"));

              /*for(int i=0;i<jsonArr.size();i++){

                  JSONObject jsonObj = (JSONObject) jsonArr.get(i);
                  System.out.println(jsonObj.get("pointX") + " // " + jsonObj.get("pointY"));
              }*/

              String rst = getDataAsJson(jsonArr);

              //System.out.println(rst);

              return rst;

          } catch (Exception e) {
             return null;
          }
      }

      public String getDataAsJson(JSONArray jsonArr) throws Exception{
          StringWriter writer = new StringWriter(8192);
              final JSONBuilder jsonWriter = new JSONBuilder(writer);
              jsonWriter.object().key("type").value("FeatureCollection");
              jsonWriter.key("features");
              jsonWriter.array();

              if (jsonArr == null || jsonArr.size() == 0) {
                  jsonWriter.endArray(); // end features
                  jsonWriter.key("totalFeatures").value(0);
                  jsonWriter.endObject(); // end featurecollection
                  writer.flush();
              } else {
                  for(int i=0;i<jsonArr.size();i++){

                      JSONObject jsonObj = (JSONObject) jsonArr.get(i);
                      //System.out.println(jsonObj.get("pointX") + " // " + jsonObj.get("pointY"));

                      jsonWriter.object(); // feature start
                      jsonWriter.key("type").value("Feature");
                      jsonWriter.key("geometry");
                      jsonWriter.object(); // geometry start
                      jsonWriter.key("type").value("Point");
                      jsonWriter.key("coordinates"); //기준 라벨 위치....
                      jsonWriter.array();
                      jsonWriter.value(jsonObj.get("pointX"));
                      jsonWriter.value(jsonObj.get("pointY"));
                      jsonWriter.endArray();
                      jsonWriter.endObject(); // geometry end
                      //
                      jsonWriter.key("properties");
                      jsonWriter.object(); // properties start
                      jsonWriter.key("symCd").value(jsonObj.get("symCd"));
                      jsonWriter.key("lkInfoId").value(jsonObj.get("lkInfoId"));
                      jsonWriter.key("seqNo").value(jsonObj.get("seqNo"));
                      jsonWriter.endObject(); // properties end
                      //
                      jsonWriter.endObject(); // feature end
                  } //jfor

                  jsonWriter.endArray(); // features array end
                  jsonWriter.endObject(); //featureCollection end

                  writer.flush();

              }


              return writer.toString();
      }

      /**
       * 긴급재난상황 지도 레이어용 리스트를 조회합니다.
       *
       * @param model
       * @param map
       * @throws Exception
       */
      @NoSession
      @RequestMapping(value = "/getEliLayerList.json", method = RequestMethod.POST)
      public void getEliLayerList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

          HashMap<String, List<String>> param = null;
          List<String> list = new ArrayList<String>();

          if (map.get("symList") != null){
              param = new HashMap<String, List<String>>();
              String[] arr = map.get("symList").split(",");
              for(int i=0; i<arr.length; i++){
                  System.out.println(arr[i]);
                  list.add(arr[i]);
              }
              param.put("symList", list);
          }

          model.addAttribute("result", eliLayer.getList(param));

      }

      /**
       * 긴급재난상황 로그 통계 뷰를 리턴합니다.
       *
       * @param model
       * @param session
       * @return view
       * @throws Exception
       */
      @RequestMapping(value = "/getEliLogStatView.do")
      public String getEliLogStatView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

          model.addAttribute("eli", eliLogStat.getEliList(map));
          model.addAttribute("map", map);

          return "/log/stat/eliLogStatView";
      }

}
