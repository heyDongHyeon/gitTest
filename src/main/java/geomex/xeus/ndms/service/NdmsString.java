package geomex.xeus.ndms.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.measure.unit.SystemOfUnits;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import geomex.xeus.util.code.DateUtil;
import geomex.xeus.util.code.StrUtil;

public class NdmsString {

	/**
	 * NDMS API URL 요청을 한다.
	 *
	 * @param url api 요청 주소
	 * @return
	 */

	public static StringBuilder getNdmsOld119() {
		StringBuilder sb = new StringBuilder();
		sb.append("[\r\n" + 
				"	{\r\n" + 
				"		\"document\": {\r\n" + 
				"			\"request\": {\r\n" + 
				"				\"serviceKey\": \"A5D8E0A234493773FE0E1B88AE8D7A40\",\r\n" + 
				"				\"fromDateTime\": \"20200816\",\r\n" + 
				"				\"infoId\": \"CM_DSR\",\r\n" + 
				"				\"sqlType\": \"json\",\r\n" + 
				"				\"userId\": \"20180124\",\r\n" + 
				"				\"areaCd\": \"36\"\r\n" + 
				"			},\r\n" + 
				"			\"rows\": {\r\n" + 
				"				\"row\": [\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597089\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80007\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"107\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 나성동\",\r\n" + 
				"						\"dsrHouseNo\": \"775\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060039\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합05\",\r\n" + 
				"						\"statEndDtime\": \"20200817161630\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603303\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"223421.5\",\r\n" + 
				"						\"gisY\": \"331616.9\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597089\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"361102000002\",\r\n" + 
				"						\"emdSn\": \"02\",\r\n" + 
				"						\"undgrdFg\": \"0\",\r\n" + 
				"						\"buldMnnm\": \"194\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"3\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597186\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80039\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"106\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 한솔동\",\r\n" + 
				"						\"dsrHouseNo\": \"3-4\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060200\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816161614\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603303\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"223097.7\",\r\n" + 
				"						\"gisY\": \"330583.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597186\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597188\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80039\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"106\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 한솔동\",\r\n" + 
				"						\"dsrHouseNo\": \"3-4\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060212\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816161611\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603303\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"223097.7\",\r\n" + 
				"						\"gisY\": \"330583.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597188\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597190\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80039\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"106\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 한솔동\",\r\n" + 
				"						\"dsrHouseNo\": \"3-4\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060212\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816161607\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603303\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"223097.7\",\r\n" + 
				"						\"gisY\": \"330583.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597190\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597076\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000006\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"110\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 어진동\",\r\n" + 
				"						\"dsrHouseNo\": \"1-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060028\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합05\",\r\n" + 
				"						\"statEndDtime\": \"20200816154942\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"224114.2\",\r\n" + 
				"						\"gisY\": \"333997.5\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597076\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597103\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80184\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"330\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 부강면\",\r\n" + 
				"						\"dsrHouseNo\": \"0-0\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060004\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합06\",\r\n" + 
				"						\"statEndDtime\": \"20200816153644\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire206\",\r\n" + 
				"						\"jurisWardId\": \"\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"233204.9\",\r\n" + 
				"						\"gisY\": \"336885.9\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597103\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597158\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80062\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"250\",\r\n" + 
				"						\"dsrRiCd\": \"34\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 조치원읍 서창리\",\r\n" + 
				"						\"dsrHouseNo\": \"2-6\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060028\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816153505\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"225993.1\",\r\n" + 
				"						\"gisY\": \"345324\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597158\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597159\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80062\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"250\",\r\n" + 
				"						\"dsrRiCd\": \"34\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 조치원읍 서창리\",\r\n" + 
				"						\"dsrHouseNo\": \"2-6\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060028\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816153043\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"225993.1\",\r\n" + 
				"						\"gisY\": \"345324\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597159\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597097\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80067\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"360\",\r\n" + 
				"						\"dsrRiCd\": \"27\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 연서면 청라리\",\r\n" + 
				"						\"dsrHouseNo\": \"166-5\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040002\",\r\n" + 
				"						\"dsrClsCd\": \"3200001\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합06\",\r\n" + 
				"						\"statEndDtime\": \"20200816152426\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602304\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"218516.9\",\r\n" + 
				"						\"gisY\": \"343983.6\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597097\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"361104574368\",\r\n" + 
				"						\"emdSn\": \"01\",\r\n" + 
				"						\"undgrdFg\": \"0\",\r\n" + 
				"						\"buldMnnm\": \"52\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"3\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597093\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80091\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"340\",\r\n" + 
				"						\"dsrRiCd\": \"23\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 금남면 감성리\",\r\n" + 
				"						\"dsrHouseNo\": \"8-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060205\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합06\",\r\n" + 
				"						\"statEndDtime\": \"20200816151628\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603304\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"226125.3\",\r\n" + 
				"						\"gisY\": \"327153.1\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597093\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597098\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80056\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"250\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 조치원읍\",\r\n" + 
				"						\"dsrHouseNo\": \"43-2\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060028\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합05\",\r\n" + 
				"						\"statEndDtime\": \"20200816151621\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"226964.1\",\r\n" + 
				"						\"gisY\": \"344591.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597098\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597096\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000006\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"110\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 어진동\",\r\n" + 
				"						\"dsrHouseNo\": \"1-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060004\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합06\",\r\n" + 
				"						\"statEndDtime\": \"20200816151616\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"224114.2\",\r\n" + 
				"						\"gisY\": \"333997.5\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597096\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597136\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000002\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"113\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 아름동\",\r\n" + 
				"						\"dsrHouseNo\": \"769\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"36000001135283\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060205\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816151611\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603302\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"222222.9\",\r\n" + 
				"						\"gisY\": \"334834\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597136\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597147\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80056\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"250\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 조치원읍\",\r\n" + 
				"						\"dsrHouseNo\": \"43-2\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060004\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816151605\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"226964.1\",\r\n" + 
				"						\"gisY\": \"344591.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597147\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597074\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80008\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"109\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 다정동\",\r\n" + 
				"						\"dsrHouseNo\": \"78-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060039\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816151408\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire225\",\r\n" + 
				"						\"jurisWardId\": \"3603303\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"222285.9\",\r\n" + 
				"						\"gisY\": \"333274.7\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597074\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"361103351284\",\r\n" + 
				"						\"emdSn\": \"01\",\r\n" + 
				"						\"undgrdFg\": \"0\",\r\n" + 
				"						\"buldMnnm\": \"77\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"3\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597048\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000002\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"113\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 아름동\",\r\n" + 
				"						\"dsrHouseNo\": \"1297\",\r\n" + 
				"						\"dsrEtcAddr\": \"(범지기마을3단지, 312동)\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060039\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합05\",\r\n" + 
				"						\"statEndDtime\": \"20200816151405\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire225\",\r\n" + 
				"						\"jurisWardId\": \"3603302\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"221900.8\",\r\n" + 
				"						\"gisY\": \"335467.6\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597048\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"361103258104\",\r\n" + 
				"						\"emdSn\": \"01\",\r\n" + 
				"						\"undgrdFg\": \"0\",\r\n" + 
				"						\"buldMnnm\": \"201\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"3\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"(아름동, 범지기마을3단지, 312동)\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597118\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80133\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"370\",\r\n" + 
				"						\"dsrRiCd\": \"26\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 전의면 유천리\",\r\n" + 
				"						\"dsrHouseNo\": \"843-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040002\",\r\n" + 
				"						\"dsrClsCd\": \"0040045\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36_____sjfire206\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816142658\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602302\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"214306.6\",\r\n" + 
				"						\"gisY\": \"354437.1\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597118\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597102\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80080\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"310\",\r\n" + 
				"						\"dsrRiCd\": \"25\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 연기면 세종리\",\r\n" + 
				"						\"dsrHouseNo\": \"747-423\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060029\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합06\",\r\n" + 
				"						\"statEndDtime\": \"20200816140420\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire206\",\r\n" + 
				"						\"jurisWardId\": \"3603302\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"225338.6\",\r\n" + 
				"						\"gisY\": \"331211.7\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597102\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597082\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80056\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"250\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 조치원읍\",\r\n" + 
				"						\"dsrHouseNo\": \"43-2\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060028\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합01\",\r\n" + 
				"						\"statEndDtime\": \"20200816135440\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"226964.1\",\r\n" + 
				"						\"gisY\": \"344591.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597082\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597090\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80012\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"103\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 보람동\",\r\n" + 
				"						\"dsrHouseNo\": \"640-3\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040002\",\r\n" + 
				"						\"dsrClsCd\": \"3200001\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합01\",\r\n" + 
				"						\"statEndDtime\": \"20200816135436\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603304\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"226332.3\",\r\n" + 
				"						\"gisY\": \"331013.3\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597090\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597072\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80159\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"390\",\r\n" + 
				"						\"dsrRiCd\": \"23\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 소정면 대곡리\",\r\n" + 
				"						\"dsrHouseNo\": \"14\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040002\",\r\n" + 
				"						\"dsrClsCd\": \"3200001\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합04\",\r\n" + 
				"						\"statEndDtime\": \"20200816134926\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602302\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"216158.5\",\r\n" + 
				"						\"gisY\": \"358036\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597072\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"361103258042\",\r\n" + 
				"						\"emdSn\": \"02\",\r\n" + 
				"						\"undgrdFg\": \"0\",\r\n" + 
				"						\"buldMnnm\": \"493\",\r\n" + 
				"						\"buldSlno\": \"21\",\r\n" + 
				"						\"addrType\": \"3\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597071\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80184\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"330\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 부강면\",\r\n" + 
				"						\"dsrHouseNo\": \"0-0\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060004\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합01\",\r\n" + 
				"						\"statEndDtime\": \"20200816134914\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"233204.9\",\r\n" + 
				"						\"gisY\": \"336885.9\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597071\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597029\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80124\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"380\",\r\n" + 
				"						\"dsrRiCd\": \"29\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 전동면 미곡리\",\r\n" + 
				"						\"dsrHouseNo\": \"334-3\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060205\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816125717\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602304\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"219633.7\",\r\n" + 
				"						\"gisY\": \"352227.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597029\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597058\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80157\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"390\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 소정면\",\r\n" + 
				"						\"dsrHouseNo\": \"0-0\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060028\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816125623\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"214141\",\r\n" + 
				"						\"gisY\": \"358208.6\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597058\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597023\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80186\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"330\",\r\n" + 
				"						\"dsrRiCd\": \"28\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 부강면 금호리\",\r\n" + 
				"						\"dsrHouseNo\": \"65-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"36000001130963\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060212\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합04\",\r\n" + 
				"						\"statEndDtime\": \"20200816124522\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602303\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"233160.5\",\r\n" + 
				"						\"gisY\": \"334830.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597023\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"0\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597012\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80123\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"380\",\r\n" + 
				"						\"dsrRiCd\": \"24\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 전동면 석곡리\",\r\n" + 
				"						\"dsrHouseNo\": \"86-8\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060205\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합05\",\r\n" + 
				"						\"statEndDtime\": \"20200816124515\",\r\n" + 
				"						\"endUserId\": \"36_____kookii127\",\r\n" + 
				"						\"jurisWardId\": \"3602304\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"224161.4\",\r\n" + 
				"						\"gisY\": \"350350.6\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597012\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596930\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000001\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"114\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 도담동\",\r\n" + 
				"						\"dsrHouseNo\": \"93-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060039\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합01\",\r\n" + 
				"						\"statEndDtime\": \"20200816123440\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"223778.7\",\r\n" + 
				"						\"gisY\": \"334720.1\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596930\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597046\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80006\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"112\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 고운동\",\r\n" + 
				"						\"dsrHouseNo\": \"739-22\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"1710621\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816123034\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603302\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"221358.6\",\r\n" + 
				"						\"gisY\": \"333539.6\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597046\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"0\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596957\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80085\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"310\",\r\n" + 
				"						\"dsrRiCd\": \"21\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 연기면 연기리\",\r\n" + 
				"						\"dsrHouseNo\": \"3-15\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060078\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합04\",\r\n" + 
				"						\"statEndDtime\": \"20200816122907\",\r\n" + 
				"						\"endUserId\": \"36_____kookii127\",\r\n" + 
				"						\"jurisWardId\": \"3603302\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"225098.8\",\r\n" + 
				"						\"gisY\": \"338793.6\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596957\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"0\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597021\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000002\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"113\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 아름동\",\r\n" + 
				"						\"dsrHouseNo\": \"산63\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060205\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합04\",\r\n" + 
				"						\"statEndDtime\": \"20200816120452\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603302\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"222086.3\",\r\n" + 
				"						\"gisY\": \"335358.6\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597021\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"0\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597031\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80061\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"250\",\r\n" + 
				"						\"dsrRiCd\": \"33\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 조치원읍 봉산리\",\r\n" + 
				"						\"dsrHouseNo\": \"167\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040002\",\r\n" + 
				"						\"dsrClsCd\": \"3200001\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합01\",\r\n" + 
				"						\"statEndDtime\": \"20200816120241\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602301\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"224860.7\",\r\n" + 
				"						\"gisY\": \"345500.2\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597031\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596939\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80107\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"340\",\r\n" + 
				"						\"dsrRiCd\": \"37\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 금남면 호탄리\",\r\n" + 
				"						\"dsrHouseNo\": \"176-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060205\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합01\",\r\n" + 
				"						\"statEndDtime\": \"20200816115312\",\r\n" + 
				"						\"endUserId\": \"36_____kookii127\",\r\n" + 
				"						\"jurisWardId\": \"3603304\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"226728.5\",\r\n" + 
				"						\"gisY\": \"330440.6\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596939\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"361104574553\",\r\n" + 
				"						\"emdSn\": \"01\",\r\n" + 
				"						\"undgrdFg\": \"0\",\r\n" + 
				"						\"buldMnnm\": \"48\",\r\n" + 
				"						\"buldSlno\": \"38\",\r\n" + 
				"						\"addrType\": \"3\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597027\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80145\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"370\",\r\n" + 
				"						\"dsrRiCd\": \"21\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 전의면 읍내리\",\r\n" + 
				"						\"dsrHouseNo\": \"188-4\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"36000001091003\",\r\n" + 
				"						\"dsrKndCd\": \"0040002\",\r\n" + 
				"						\"dsrClsCd\": \"3200001\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816115116\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602302\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"218121.9\",\r\n" + 
				"						\"gisY\": \"353741.3\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597027\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597015\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80039\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"106\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 한솔동\",\r\n" + 
				"						\"dsrHouseNo\": \"3-4\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060028\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합05\",\r\n" + 
				"						\"statEndDtime\": \"20200816114701\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603303\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"223097.7\",\r\n" + 
				"						\"gisY\": \"330583.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597015\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596960\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80130\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"370\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 전의면\",\r\n" + 
				"						\"dsrHouseNo\": \"0-0\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060028\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합01\",\r\n" + 
				"						\"statEndDtime\": \"20200816113957\",\r\n" + 
				"						\"endUserId\": \"36_____kookii127\",\r\n" + 
				"						\"jurisWardId\": \"\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"217517\",\r\n" + 
				"						\"gisY\": \"353678.3\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596960\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596993\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80056\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"250\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 조치원읍\",\r\n" + 
				"						\"dsrHouseNo\": \"43-2\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060028\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합01\",\r\n" + 
				"						\"statEndDtime\": \"20200816113936\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"226964.1\",\r\n" + 
				"						\"gisY\": \"344591.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596993\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596925\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000002\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"113\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 아름동\",\r\n" + 
				"						\"dsrHouseNo\": \"산65\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060039\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816113932\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603302\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"222359.6\",\r\n" + 
				"						\"gisY\": \"335460\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596925\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596695\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80013\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"108\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 새롬동\",\r\n" + 
				"						\"dsrHouseNo\": \"산30\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0500026\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합05\",\r\n" + 
				"						\"statEndDtime\": \"20200816113557\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire225\",\r\n" + 
				"						\"jurisWardId\": \"3603303\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"222318\",\r\n" + 
				"						\"gisY\": \"332292.5\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596695\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596935\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000001\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"114\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 도담동\",\r\n" + 
				"						\"dsrHouseNo\": \"330-3\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060039\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816113249\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"223024.4\",\r\n" + 
				"						\"gisY\": \"334635.1\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596935\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596944\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80085\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"310\",\r\n" + 
				"						\"dsrRiCd\": \"21\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 연기면 연기리\",\r\n" + 
				"						\"dsrHouseNo\": \"1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060200\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816111618\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603302\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"225125.8\",\r\n" + 
				"						\"gisY\": \"338930.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596944\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600597001\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80109\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"340\",\r\n" + 
				"						\"dsrRiCd\": \"38\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 금남면 신촌리\",\r\n" + 
				"						\"dsrHouseNo\": \"80\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040002\",\r\n" + 
				"						\"dsrClsCd\": \"3200001\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816111611\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603304\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"225798.4\",\r\n" + 
				"						\"gisY\": \"330138.7\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600597001\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"361104574343\",\r\n" + 
				"						\"emdSn\": \"01\",\r\n" + 
				"						\"undgrdFg\": \"0\",\r\n" + 
				"						\"buldMnnm\": \"35\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"3\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596926\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80008\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"109\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 다정동\",\r\n" + 
				"						\"dsrHouseNo\": \"634\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060205\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816110127\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603303\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"221836.4\",\r\n" + 
				"						\"gisY\": \"332747.7\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596926\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596984\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000006\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"110\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 어진동\",\r\n" + 
				"						\"dsrHouseNo\": \"1-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060028\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816110106\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"224114.2\",\r\n" + 
				"						\"gisY\": \"333997.5\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596984\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596963\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80006\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"112\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 고운동\",\r\n" + 
				"						\"dsrHouseNo\": \"739-113\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060205\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816110103\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603302\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"221484.8\",\r\n" + 
				"						\"gisY\": \"334329\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596963\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"0\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596941\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80012\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"103\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 보람동\",\r\n" + 
				"						\"dsrHouseNo\": \"38-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060028\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합01\",\r\n" + 
				"						\"statEndDtime\": \"20200816104355\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603304\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"226606.2\",\r\n" + 
				"						\"gisY\": \"331061.2\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596941\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596967\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000006\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"110\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 어진동\",\r\n" + 
				"						\"dsrHouseNo\": \"1-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060200\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816104340\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"224114.2\",\r\n" + 
				"						\"gisY\": \"333997.5\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596967\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596986\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80078\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"360\",\r\n" + 
				"						\"dsrRiCd\": \"33\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 연서면 봉암리\",\r\n" + 
				"						\"dsrHouseNo\": \"646\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"36000001129960\",\r\n" + 
				"						\"dsrKndCd\": \"0040002\",\r\n" + 
				"						\"dsrClsCd\": \"3200001\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36_____sjfire298\",\r\n" + 
				"						\"ttyId\": \"종합01\",\r\n" + 
				"						\"statEndDtime\": \"20200816104337\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3602304\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"224981.2\",\r\n" + 
				"						\"gisY\": \"340013.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596986\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596974\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000001\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"114\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 도담동\",\r\n" + 
				"						\"dsrHouseNo\": \"200-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"1710620\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816104332\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603301\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"223178.6\",\r\n" + 
				"						\"gisY\": \"334907\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596974\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596940\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000006\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"110\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 어진동\",\r\n" + 
				"						\"dsrHouseNo\": \"1-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060200\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816100301\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"224114.2\",\r\n" + 
				"						\"gisY\": \"333997.5\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596940\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596955\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000001\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"114\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 도담동\",\r\n" + 
				"						\"dsrHouseNo\": \"산69\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"36000001127611\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060212\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합01\",\r\n" + 
				"						\"statEndDtime\": \"20200816095750\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603301\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"223503.1\",\r\n" + 
				"						\"gisY\": \"335196.1\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596955\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596928\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000002\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"113\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 아름동\",\r\n" + 
				"						\"dsrHouseNo\": \"산41\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040002\",\r\n" + 
				"						\"dsrClsCd\": \"3200001\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36_____sjfire298\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816092148\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603302\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"222409.3\",\r\n" + 
				"						\"gisY\": \"334703\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596928\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596929\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000006\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"110\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 어진동\",\r\n" + 
				"						\"dsrHouseNo\": \"1-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060200\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816092142\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire246\",\r\n" + 
				"						\"jurisWardId\": \"3603301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"224114.2\",\r\n" + 
				"						\"gisY\": \"333997.5\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596929\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"Y\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596910\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80078\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"360\",\r\n" + 
				"						\"dsrRiCd\": \"33\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 연서면 봉암리\",\r\n" + 
				"						\"dsrHouseNo\": \"496-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060039\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합05\",\r\n" + 
				"						\"statEndDtime\": \"20200816092004\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire225\",\r\n" + 
				"						\"jurisWardId\": \"3602304\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"225683\",\r\n" + 
				"						\"gisY\": \"339990.2\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596910\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596924\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000006\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"110\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 어진동\",\r\n" + 
				"						\"dsrHouseNo\": \"1-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060200\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"Y\",\r\n" + 
				"						\"ctlUserId\": \"36_____sjfire067\",\r\n" + 
				"						\"ttyId\": \"종합04\",\r\n" + 
				"						\"statEndDtime\": \"20200816090010\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire298\",\r\n" + 
				"						\"jurisWardId\": \"3603301\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"2\",\r\n" + 
				"						\"gisX\": \"224114.2\",\r\n" + 
				"						\"gisY\": \"333997.5\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596924\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596886\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80008\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"109\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 다정동\",\r\n" + 
				"						\"dsrHouseNo\": \"산29\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060205\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816080322\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire298\",\r\n" + 
				"						\"jurisWardId\": \"3603303\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"221726.5\",\r\n" + 
				"						\"gisY\": \"333029.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596886\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"0\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596888\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80158\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"390\",\r\n" + 
				"						\"dsrRiCd\": \"24\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 소정면 고등리\",\r\n" + 
				"						\"dsrHouseNo\": \"85\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060212\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"Y\",\r\n" + 
				"						\"ctlUserId\": \"36_____sjfire206\",\r\n" + 
				"						\"ttyId\": \"종합06\",\r\n" + 
				"						\"statEndDtime\": \"20200816080312\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire034\",\r\n" + 
				"						\"jurisWardId\": \"3602302\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"217874.2\",\r\n" + 
				"						\"gisY\": \"357522.8\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596888\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"0\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596885\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80062\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"250\",\r\n" + 
				"						\"dsrRiCd\": \"34\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 조치원읍 서창리\",\r\n" + 
				"						\"dsrHouseNo\": \"208\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"36SJ0000024228\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"1710619\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합02\",\r\n" + 
				"						\"statEndDtime\": \"20200816074616\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire197\",\r\n" + 
				"						\"jurisWardId\": \"3602301\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"225373.3\",\r\n" + 
				"						\"gisY\": \"345713.4\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596885\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596875\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80005\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"105\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 가람동\",\r\n" + 
				"						\"dsrHouseNo\": \"799-2\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0040009\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합10\",\r\n" + 
				"						\"statEndDtime\": \"20200816060602\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire197\",\r\n" + 
				"						\"jurisWardId\": \"3603303\",\r\n" + 
				"						\"handInsYn\": \"Y\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"222067.2\",\r\n" + 
				"						\"gisY\": \"331136.7\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596875\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596880\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80181\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"350\",\r\n" + 
				"						\"dsrRiCd\": \"26\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 장군면 하봉리\",\r\n" + 
				"						\"dsrHouseNo\": \"274-3\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"1710619\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합06\",\r\n" + 
				"						\"statEndDtime\": \"20200816060558\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire197\",\r\n" + 
				"						\"jurisWardId\": \"3603305\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"216345.2\",\r\n" + 
				"						\"gisY\": \"333674.1\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596880\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"0\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596882\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000007\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"340\",\r\n" + 
				"						\"dsrRiCd\": \"21\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 금남면 용포리\",\r\n" + 
				"						\"dsrHouseNo\": \"92-4\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"36SJ0000024495\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060212\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합10\",\r\n" + 
				"						\"statEndDtime\": \"20200816060552\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire197\",\r\n" + 
				"						\"jurisWardId\": \"3603304\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"225186.7\",\r\n" + 
				"						\"gisY\": \"329546.7\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596882\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"0\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596879\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80006\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"112\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 고운동\",\r\n" + 
				"						\"dsrHouseNo\": \"산163\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"0060205\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합05\",\r\n" + 
				"						\"statEndDtime\": \"20200816040832\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire197\",\r\n" + 
				"						\"jurisWardId\": \"3603302\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"221521.8\",\r\n" + 
				"						\"gisY\": \"335341\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596879\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596842\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80013\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"108\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 새롬동\",\r\n" + 
				"						\"dsrHouseNo\": \"305-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"1710619\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합10\",\r\n" + 
				"						\"statEndDtime\": \"20200816030436\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire197\",\r\n" + 
				"						\"jurisWardId\": \"3603303\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"222401.5\",\r\n" + 
				"						\"gisY\": \"332137.4\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596842\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596872\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80057\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"250\",\r\n" + 
				"						\"dsrRiCd\": \"24\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 조치원읍 교리\",\r\n" + 
				"						\"dsrHouseNo\": \"28-1\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"0040003\",\r\n" + 
				"						\"dsrClsCd\": \"1710619\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합03\",\r\n" + 
				"						\"statEndDtime\": \"20200816014542\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire298\",\r\n" + 
				"						\"jurisWardId\": \"3602301\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"226698.3\",\r\n" + 
				"						\"gisY\": \"344987\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596872\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"1\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596865\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"1000005\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"111\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 종촌동\",\r\n" + 
				"						\"dsrHouseNo\": \"151-87\",\r\n" + 
				"						\"dsrEtcAddr\": \"\",\r\n" + 
				"						\"objId\": \"36000001129187\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060212\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36_____sjfire298\",\r\n" + 
				"						\"ttyId\": \"종합10\",\r\n" + 
				"						\"statEndDtime\": \"20200816003752\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire034\",\r\n" + 
				"						\"jurisWardId\": \"3603302\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"222364.2\",\r\n" + 
				"						\"gisY\": \"334064.5\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596865\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"0\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"dsrSeq\": \"UV3600596863\",\r\n" + 
				"						\"samedsrSeq\": \"\",\r\n" + 
				"						\"dsrZipSeq\": \"80012\",\r\n" + 
				"						\"dsrSidoCd\": \"36\",\r\n" + 
				"						\"dsrGugunCd\": \"110\",\r\n" + 
				"						\"dsrDongCd\": \"103\",\r\n" + 
				"						\"dsrRiCd\": \"00\",\r\n" + 
				"						\"userDefineAreaNm\": \"세종시 세종특별자치시 보람동\",\r\n" + 
				"						\"dsrHouseNo\": \"700-87\",\r\n" + 
				"						\"dsrEtcAddr\": \"(엘레베이터용)\",\r\n" + 
				"						\"objId\": \"\",\r\n" + 
				"						\"dsrKndCd\": \"13306\",\r\n" + 
				"						\"dsrClsCd\": \"0060212\",\r\n" + 
				"						\"dsrSizeCd\": \"0050001\",\r\n" + 
				"						\"procCd\": \"0140060\",\r\n" + 
				"						\"ctlYn\": \"N\",\r\n" + 
				"						\"ctlUserId\": \"36\",\r\n" + 
				"						\"ttyId\": \"종합10\",\r\n" + 
				"						\"statEndDtime\": \"20200816003656\",\r\n" + 
				"						\"endUserId\": \"36_____sjfire034\",\r\n" + 
				"						\"jurisWardId\": \"3603304\",\r\n" + 
				"						\"handInsYn\": \"N\",\r\n" + 
				"						\"radioCh\": \"\",\r\n" + 
				"						\"stationId\": \"\",\r\n" + 
				"						\"pointAccrCls\": \"1\",\r\n" + 
				"						\"gisX\": \"225666.2\",\r\n" + 
				"						\"gisY\": \"330942.3\",\r\n" + 
				"						\"samedsrRad\": \"\",\r\n" + 
				"						\"dFstRegSeq\": \"UV3600596863\",\r\n" + 
				"						\"ctrlrscDspReqYn\": \"\",\r\n" + 
				"						\"aftRegYn\": \"\",\r\n" + 
				"						\"dspDelayRsnCd\": \"\",\r\n" + 
				"						\"airDsrSeq\": \"\",\r\n" + 
				"						\"addrFlag\": \"1\",\r\n" + 
				"						\"avlGisX\": \"\",\r\n" + 
				"						\"avlGisY\": \"\",\r\n" + 
				"						\"awareYn\": \"\",\r\n" + 
				"						\"breathYn\": \"\",\r\n" + 
				"						\"cprYn\": \"\",\r\n" + 
				"						\"refuseYn\": \"\",\r\n" + 
				"						\"rnCd\": \"\",\r\n" + 
				"						\"emdSn\": \"\",\r\n" + 
				"						\"undgrdFg\": \"\",\r\n" + 
				"						\"buldMnnm\": \"0\",\r\n" + 
				"						\"buldSlno\": \"0\",\r\n" + 
				"						\"addrType\": \"0\",\r\n" + 
				"						\"dsrRnEtcAddr\": \"\",\r\n" + 
				"						\"zoneId\": \"\",\r\n" + 
				"						\"noemerYn\": \"\"\r\n" + 
				"					}\r\n" + 
				"				]\r\n" + 
				"			},\r\n" + 
				"			\"statuses\": {\r\n" + 
				"				\"status\": [\r\n" + 
				"					{\r\n" + 
				"						\"code\": \"200\",\r\n" + 
				"						\"message\": \"정상 처리\"\r\n" + 
				"					}\r\n" + 
				"				],\r\n" + 
				"				\"rowCount\": 65,\r\n" + 
				"				\"totalCount\": \"65\",\r\n" + 
				"				\"pageNum\": 1,\r\n" + 
				"				\"pageSize\": 100\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"]");
		return sb;
	}
	public static StringBuilder getNdmsNew119() {
		StringBuilder sb = new StringBuilder();
		sb.append("[\r\n" + 
				"	{\r\n" + 
				"		\"document\": {\r\n" + 
				"			\"request\": {\r\n" + 
				"				\"serviceKey\": \"FA80355F750CFBF729CA15CA026B0286\",\r\n" + 
				"				\"fromDateTime\": \"20200816\",\r\n" + 
				"				\"infoId\": \"INF_119_MSTR\",\r\n" + 
				"				\"sqlType\": \"json\",\r\n" + 
				"				\"userId\": \"20200509\",\r\n" + 
				"				\"areaCd\": \"36\"\r\n" + 
				"			},\r\n" + 
				"			\"rows\": {\r\n" + 
				"				\"row\": [\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200817171334\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 조치원읍 번암리(연기경찰서 세종경찰서)\",\r\n" + 
				"						\"reptDesc\": \"전의파출소/학생 비출혈\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 전의면 읍내리265-3 (연기경찰서 세종경찰서)\",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 16:19:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597196\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 16:17:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200817161250\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 조치원읍 상리(2층)\",\r\n" + 
				"						\"reptDesc\": \"조치원로 42 // 2층 및 지하에서 울린다. // 화재상황은 없음 // 하나의원 건물 // 010-2739-8639\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 조치원읍 상리138-3 (2층)\",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"기타출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 16:19:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3700597195\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 16:15:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816160104\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"[대상자명]도민지  [대상자 전화번호]01097794666  [대상자 인근정보]  [출동요청시각]202008161630  [신청사유]<장군 606> / 17:00 / 새롬북로 13 1302-1901 / 010-9779-4666\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 새롬동373 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"구급예약\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 16:16:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597191\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 16:04:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816155950\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"방송점검\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 한솔동3-4 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"기타출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소완료보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 16:14:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597190\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 16:03:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816155919\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"방송점검\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 한솔동3-4 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"기타출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소완료보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 16:14:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597188\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 16:02:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816155632\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"방송 점검\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 한솔동3-4 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"업무운행\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소완료보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 16:11:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597186\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 15:59:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816155434\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 새롬동\",\r\n" + 
				"						\"reptDesc\": \"교통사고 퀴보드 차량사고 환자 1명 //  새롬동 해피라움 주차장 입구\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 새롬동산5 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"교통사고\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 16:09:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597185\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 15:59:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816154959\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"벌집제거 / 오류골길 100-8\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 전의면 유천리689-2 \",\r\n" + 
				"						\"pttnNm1\": \"구조\",\r\n" + 
				"						\"pttnNm2\": \"벌집제거\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장출발\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 16:04:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597184\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 15:53:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816154800\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"301 순찰등\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 전의면0-0 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 16:03:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597182\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 15:51:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816153351\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"속보기 작동 / 조치원읍 서창리140 - 21 삼현빌라 C동 102호\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 조치원읍 서창리140-21 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"기타출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소완료보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 15:48:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597171\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 15:37:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816152200\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"211 순찰\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 조치원읍43-2 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 15:37:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597167\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 15:25:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816151300\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"901물놀이위험지역 순찰\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 조치원읍 서창리2-6 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 15:28:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597159\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 15:17:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816151229\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"[대상자명]evandro  [대상자 전화번호]01041091979  [대상자 인근정보]  [출동요청시각]202008161540  [신청사유]<어진 206> / 16:15 /  evandro / 금남면 용포리 220-6 세종빌 401호 / 010-4109-1979\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 대평동484-8 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"구급예약\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 15:27:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597157\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 15:15:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816151200\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"202순찰\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 조치원읍 서창리2-6 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 15:27:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597158\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 15:15:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816151100\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"남자 복통 // 코로나 관련 없음\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 전동면 보덕리산2-1 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 15:26:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597160\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 15:17:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816151050\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"벌집 // 금남면 성덕영곡길 109\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 금남면 성덕리488 \",\r\n" + 
				"						\"pttnNm1\": \"구조\",\r\n" + 
				"						\"pttnNm2\": \"벌집제거\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 15:25:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597155\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 15:15:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816150106\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"[대상자명]황광숙  [대상자 전화번호]01098762439  [대상자 인근정보]  [출동요청시각]202008161530  [신청사유]<원리 216> / 16:30 / 황광숙 외 2명 / 보듬4로 9 1102-2502 / 010-9876-2439\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 도담동653 (도램마을 11단지, 1103동)\",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 15:16:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597150\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 15:04:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816150100\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"순찰\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 한솔동3-4 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 15:16:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597153\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 15:07:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816145200\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"신호제어관련 등\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 조치원읍43-2 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"훈련출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 15:07:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597147\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 14:59:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816143318\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 아름동\",\r\n" + 
				"						\"reptDesc\": \"아름동 싱싱마트 앞 / 쓰러짐(동보)아름동 싱싱장터 / 쓰러짐 / 의식없고, 호흡은 있어요, 눈 못떠요 /\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 아름동769 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장출발\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 14:48:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597136\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 14:37:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816143107\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"[대상자명]박경윤  [대상자 전화번호]01042144960  [대상자 인근정보]  [출동요청시각]202008161500  [신청사유]<연서 506> / 16:00 / 박경윤 / 전의면 양달말길 36-11 / 010-4214-4960\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 전의면 신정리256-1 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동지령\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 14:46:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597135\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 14:34:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816142400\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"208 훈련\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 조치원읍43-2 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"훈련출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 14:39:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597129\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 14:27:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816141639\",\r\n" + 
				"						\"endDt\": \"20200816142658\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"구정사거리 // 도로한가운데 // sm7 옥색 // 천안방향 // 사람이 목을 뒤로 젖히고 누워있다.\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 전의면 유천리843-1 \",\r\n" + 
				"						\"pttnNm1\": \"구조\",\r\n" + 
				"						\"pttnNm2\": \"기타안전사고\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"0\",\r\n" + 
				"						\"dstrStatCd\": \"0\",\r\n" + 
				"						\"dstrStatNm\": \"상황종료\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 14:31:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597118\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 14:21:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816135900\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"불시출동 훈련\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 부강면0-0 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"훈련출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 14:14:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597103\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 14:03:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816135843\",\r\n" + 
				"						\"endDt\": \"20200816140420\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 새롬동\",\r\n" + 
				"						\"reptDesc\": \"테스트\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 연기면 세종리747-423 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"상황출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"0\",\r\n" + 
				"						\"dstrStatCd\": \"0\",\r\n" + 
				"						\"dstrStatNm\": \"상황종료\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 14:13:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597102\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 14:03:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816135300\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"205 순찰\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 어진동1-1 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 14:07:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597100\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 13:57:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816134718\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"벌집제거 / 양대길 52\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 연서면 청라리166-5 \",\r\n" + 
				"						\"pttnNm1\": \"구조\",\r\n" + 
				"						\"pttnNm2\": \"벌집제거\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 14:02:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597097\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 13:51:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816134400\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"훈련\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 어진동1-1 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"훈련출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 13:58:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597096\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 13:47:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816134300\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"지리조사\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 조치원읍43-2 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 13:57:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597098\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 13:51:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816133553\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"여자 조현병 // 국립공주병원 // 세종시 금남면 감성길 47-4 // 코로나 관련 증상 없음\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 금남면 감성리8-1 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 13:50:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597093\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 13:41:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816133142\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 보람동\",\r\n" + 
				"						\"reptDesc\": \"벌집제거 / 호려울마을 607동 601호 /\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 보람동640-3 \",\r\n" + 
				"						\"pttnNm1\": \"구조\",\r\n" + 
				"						\"pttnNm2\": \"벌집제거\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 13:46:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597090\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 13:35:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816133109\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"[대상자명]levi  [대상자 전화번호]01091808682  [대상자 인근정보]  [출동요청시각]202008161400  [신청사유]<장군 606> / 14:30 / levi / 세종의 아침 720호 / 010-9180-8682\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 나성동775 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"구급예약\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 13:46:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597089\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 13:34:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816131800\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"212순찰\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 조치원읍43-2 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 13:32:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597082\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 13:21:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816130300\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"11 순찰\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 어진동1-1 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 13:17:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597076\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 13:07:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816130110\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"[대상자명]최윤미  [대상자 전화번호]01064332675  [대상자 인근정보]  [출동요청시각]202008161330  [신청사유]<어진 206> / 14:00 / 최윤미 외 1명 / 다정중앙로 77 608-1802 / 010-6433-2675\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 다정동78-1 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 13:16:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597074\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 13:04:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816125315\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"벌집  // 소정면 대곡리 14  미소물산\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 소정면 대곡리14 \",\r\n" + 
				"						\"pttnNm1\": \"구조\",\r\n" + 
				"						\"pttnNm2\": \"벌집제거\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 13:08:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597072\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 12:59:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816124400\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"405 훈련\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 부강면0-0 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"훈련출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 12:58:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597071\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 12:47:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816121700\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"312 순찰\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 소정면0-0 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 12:31:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597058\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 12:21:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816120113\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"[대상자명]우두식  [대상자 전화번호]01065521276  [대상자 인근정보]  [출동요청시각]202008161230  [신청사유]<원리 216> / 13:30 / 우두식 / 범지기 404-1601 / 010-6552-1276\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 아름동1297 (범지기마을3단지, 312동)\",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 12:16:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597048\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 12:05:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816120004\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"[요청기관] 충남지방경찰청 [접수일시] 20200816115705\\n[재난종별] 질서유지 [접수/요청자명]이수연/권익순-세종\\n[재난위치] (지)세종특별자치시 세종시 다정동(새롬동) 419-1 (경위도) 127.2384, 36.49988\\n[신고내용] 어진중학교 에서 공주방향/ 너비뜰지나/\\n택배탑차량/\\n일차로 도로상에 서있다./\\n운전자 사망한것 같다는 신고자 언동/\\n 접수경로(112)\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 고운동739-22 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"구급기타\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소완료보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 12:15:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597046\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 12:03:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816114035\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 조치원읍 봉산리\",\r\n" + 
				"						\"reptDesc\": \"벌집제거 / 봉산리 119 / 010-4854-2382\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 조치원읍 봉산리167 \",\r\n" + 
				"						\"pttnNm1\": \"구조\",\r\n" + 
				"						\"pttnNm2\": \"벌집제거\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 11:55:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597031\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 11:43:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816113638\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"전동면 미곡리 운주산로 984  // 밖에서 일하다가 쓰러짐 의식호흡있음 기운이 없다고 함 // 코로나 관련 없다고 함\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 전동면 미곡리334-3 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 11:51:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597029\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 11:39:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816113356\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"전의성당 주차장 // 개 그물에 걸림 // 신고자 현장에서 안내\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 전의면 읍내리188-4 \",\r\n" + 
				"						\"pttnNm1\": \"구조\",\r\n" + 
				"						\"pttnNm2\": \"벌집제거\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 11:48:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597027\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 11:39:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816112948\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"부강면 냉천굴 나무가 쓰러져 있다 / 나무 쓰러짐 (추가)120 안내\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 부강면 금호리65-1 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"지원출동(풍수해)\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 11:44:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597023\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 11:33:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816112709\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"[대전소방본부]\\n아기 19개월 / 경기 / 세종시 / 구급 신고/903-503/범지기/할머니가 보는 중/어제부터 010-5624-0123/(추가)범지기 903-503(추가)아이 축 늘어짐\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 아름동산63 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 11:42:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597021\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 11:31:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816111200\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"402순찰\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 한솔동3-4 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 11:27:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597015\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 11:15:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816105843\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"행복요양원 / 40분전 HP 60/48, 수액맞은후 79/44 / 현재 말이 어눌하다 / 코로나 관련없음\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 전동면 석곡리86-8 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 11:13:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597012\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 11:01:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816104238\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"금남면 신촌1길 35 / 벌집제거 / 신고자 안내해준다고 함\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 금남면 신촌리80 \",\r\n" + 
				"						\"pttnNm1\": \"구조\",\r\n" + 
				"						\"pttnNm2\": \"벌집제거\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장출발\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 10:57:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600597001\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 10:45:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816102900\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"순찰\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 조치원읍43-2 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 10:43:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596993\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 10:32:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816101618\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"벌집제거 / 연서면 강산로 284-2 / 봉암리 646-2 / 세종액자갤러리\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 연서면 봉암리646 \",\r\n" + 
				"						\"pttnNm1\": \"구조\",\r\n" + 
				"						\"pttnNm2\": \"벌집제거\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 10:31:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596986\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 10:19:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816101500\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"예방순찰\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 어진동1-1 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 10:29:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596984\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 10:18:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816100828\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"[대전소방본부]\\n넘어져 손 골절 의심 / 세종시 보듬3로 7 / 도램마을 801동 1601호 / 신고자는 남편이며 대전에서 신고함 / 현장에있는 아내 연락처: 010-6639-6201 / 코로나 연관 없음\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 도담동200-1 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"사고부상\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 10:23:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596974\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 10:13:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816100100\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"207 주유\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 어진동1-1 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"업무운행\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 10:15:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596967\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 10:05:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816095757\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 반곡동\",\r\n" + 
				"						\"reptDesc\": \"어머니 어지러움 호소/구토/빙글빙글/가락마을 8/808-902/환자 010-6369-9038/(추가)코로나 관련 특이사항 없음\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 고운동739-113 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 10:12:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596963\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 10:01:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816095700\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"순찰\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 전의면0-0 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 10:11:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596960\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 10:00:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816095354\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"여자 수면제 복용 // 연기면 당산말길 행복빌리지 302호 // (추가)수면제 관다 복용 / 호흡있음 // 코로나 관련 파악 안됨\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 연기면 연기리3-15 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"약물중독\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 10:08:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596957\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 09:57:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816095016\",\r\n" + 
				"						\"endDt\": \"20200816095750\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 도담동\",\r\n" + 
				"						\"reptDesc\": \"가온유치원 010 9161 7819 / 화재속보기 작동 /\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 도담동산69 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"기타출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"0\",\r\n" + 
				"						\"dstrStatCd\": \"0\",\r\n" + 
				"						\"dstrStatNm\": \"상황종료\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 10:05:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596955\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 09:53:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816093325\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"502 순찰\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 보람동38-1 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"순찰출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 09:48:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596941\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 09:36:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816093300\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"312 아름센터/용기충전\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 연기면 연기리1 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"업무운행\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 09:47:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596944\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 09:37:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816093107\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 금남면\",\r\n" + 
				"						\"reptDesc\": \"호탄길 48-38 / 거동불가 / 코로나 확인불가\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 금남면 호탄리176-1 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 09:46:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596939\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 09:34:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816093100\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"291 차량수리\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 어진동1-1 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"업무운행\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동지령\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 09:45:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596940\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 09:35:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816091803\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"보건소 도착 11:00 / 정영주 외2명 / 다솜1로 109-1703 / 010-2113-6123\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 도담동330-3 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"구급예약\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 09:33:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596935\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 09:21:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816091557\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"보건소 도착 10:30 / 이정언 / 보듬2로 42 1409-1402 / 010-7748-2106\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 도담동93-1 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"구급예약\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 09:30:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596930\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 09:19:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816091500\",\r\n" + 
				"						\"endDt\": \"20200816092142\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"216 차량점검 /\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 어진동1-1 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"업무운행\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"0\",\r\n" + 
				"						\"dstrStatCd\": \"0\",\r\n" + 
				"						\"dstrStatNm\": \"상황종료\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 09:29:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596929\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 09:19:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816091233\",\r\n" + 
				"						\"endDt\": \"20200816092148\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 아름동\",\r\n" + 
				"						\"reptDesc\": \"범지기마을11단지 후문 왼쪽 펜스(1107동) / 말벌집 / 벌도 있다 / 크기는 크지않다 / 높이는 높지않다(지상에 있다) / 직원도 벌에쏘였다(동보)제거 출동취소\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 아름동산41 \",\r\n" + 
				"						\"pttnNm1\": \"구조\",\r\n" + 
				"						\"pttnNm2\": \"벌집제거\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"0\",\r\n" + 
				"						\"dstrStatCd\": \"0\",\r\n" + 
				"						\"dstrStatNm\": \"상황종료\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 09:27:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596928\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 09:17:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816090557\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 새롬동\",\r\n" + 
				"						\"reptDesc\": \"가온마을1단지 111동 102호 / 복통 / 당뇨병력(초기)있음 / 본인이 신고 / 코로나 연관 없다\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 다정동634 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 09:20:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596926\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 09:09:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816090043\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"보건소 도착 10:00 / 신철안 외 3명 / 범지기 1007-1003 / 010-5358-6517\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 아름동산65 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"구급예약\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 09:15:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596925\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 09:03:01.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816085851\",\r\n" + 
				"						\"endDt\": \"20200816090010\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"방송 및 무전기 점검\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 어진동1-1 \",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"업무운행\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"0\",\r\n" + 
				"						\"dstrStatCd\": \"0\",\r\n" + 
				"						\"dstrStatNm\": \"상황종료\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 09:13:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596924\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 09:01:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816081445\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"<코로나19 보건소 이송지원>\\n도착시간 09:30\\n윤성란, 정유호 / 연서면 봉암길78 / 010-5058-8138 / 중국입국자\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 연서면 봉암리496-1 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"구급예약\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"출동지령\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 08:29:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596910\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 08:19:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816072720\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 연서면 성제리(우리요양원)\",\r\n" + 
				"						\"reptDesc\": \"우리요양원 / 107세 어르신 / 열이 39.1 / 호흡이 안되서 / 응급(추가)010-6564-3220 / 요양사(추가)기저질환-고혈압 / 비강캐뉼라->비재호흡마스크로 교체 산소 투여 지시(동보)구급독촉\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 연서면 성제리564-2 (우리요양원)\",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 07:42:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596892\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 07:31:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816071449\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"신신제약 세종공장 경비실 // 바로 옆 하수종말처리장에서 화재경보가 계속울림 // 불꽃 연기 안보임 // (동보)신신제약 관계자/연기가  조금 나고있다\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 소정면 고등리87-1 \",\r\n" + 
				"						\"pttnNm1\": \"화재\",\r\n" + 
				"						\"pttnNm2\": \"기타화재\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 07:29:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596888\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 07:17:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816065442\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 다정동\",\r\n" + 
				"						\"reptDesc\": \"아내가 어지럼증호소 / 가온마을 219동 202호 / (추가)코로나 관련없다 함\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 다정동산29 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 07:09:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596886\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 06:57:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816064942\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"벌에 쏘였다//고려대 약대안에 들어왔다//자연대//\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 조치원읍 서창리208 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병외\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장출발\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 07:04:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596885\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 06:53:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816052928\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"금남면 신나래마트 / 소화전에서 벨소리 (추가)신나라마트\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 금남면 용포리92-4 \",\r\n" + 
				"						\"pttnNm1\": \"화재\",\r\n" + 
				"						\"pttnNm2\": \"기타화재\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 05:44:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596882\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 05:33:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816034716\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 장군면 하봉리\",\r\n" + 
				"						\"reptDesc\": \"장계하봉 재줏골 / 소변을 못본다 / 배뇨장애 / 재지골길 52(추가)코로나관련없다 함\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 장군면 하봉리274-3 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병외\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 04:02:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596880\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 03:51:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816033828\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 고운동\",\r\n" + 
				"						\"reptDesc\": \"가락마을 2008동 503호//복통//\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 고운동산163 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 03:53:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596879\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 03:41:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816023411\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 새롬동\",\r\n" + 
				"						\"reptDesc\": \"세종시 터널 // 사오리지하차도 안 세종에서 조치원가는 방향 // 첫 진입로 // 차가 뒤집혔다고 함 // 지나가다 신고함 //  사오리지하차도 중간 쯤  // 차량 전도 // 구조상황 없음 (동보)[요청기관] 충남지방경찰청 [접수일시] 20200816023109\\n[신고내용] 대전에서 세종으로 넘어 오는길/ 첫번째 터널// 터널안 차량이 전복되어 있다/  접수경로(112)(추가)환자 1명 //  (동보)사오리지하차도 / 차량전복(동보)경찰청 상황실 / 출동관련문의 / 출동했다고 안내함\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 가람동799-2 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"교통사고\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 02:49:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596875\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 02:37:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816012238\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시\",\r\n" + 
				"						\"reptDesc\": \"뿜빠이 노래방 앞에 사람이 쓰러져있다 / 다가갈수없다  / 지나가는 행인이 신고\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 조치원읍 교리28-1 \",\r\n" + 
				"						\"pttnNm1\": \"구급\",\r\n" + 
				"						\"pttnNm2\": \"질병외\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 01:37:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596872\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 01:27:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816000854\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 종촌동\",\r\n" + 
				"						\"reptDesc\": \"종촌동 메디케어빌딩 // 화재경보가 계속 울림 // 10분정도 // 불꽃 연기 안보임 (추가)관리자 010 4210 1201(추가)관계자 연락되지 않는 상황\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 종촌동151-87 \",\r\n" + 
				"						\"pttnNm1\": \"화재\",\r\n" + 
				"						\"pttnNm2\": \"기타화재\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"현장도착보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 00:23:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596865\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 00:13:00.0\"\r\n" + 
				"					},\r\n" + 
				"					{\r\n" + 
				"						\"reptDt\": \"20200816000440\",\r\n" + 
				"						\"endDt\": \"\",\r\n" + 
				"						\"orgnId\": \"\",\r\n" + 
				"						\"reptAreaNm\": \"세종특별자치시 보람동(엘레베이터용)\",\r\n" + 
				"						\"reptDesc\": \"화재속보설비 // 호려울 7단지 // 044-868-5652 // (추가)702동 1003호 //\",\r\n" + 
				"						\"dstrAreaNm\": \"세종특별자치시 보람동700-87 (엘레베이터용)\",\r\n" + 
				"						\"pttnNm1\": \"기타\",\r\n" + 
				"						\"pttnNm2\": \"기타출동\",\r\n" + 
				"						\"pttnNm3\": \"1차출동\",\r\n" + 
				"						\"dstrState\": \"\",\r\n" + 
				"						\"causNm\": \"\",\r\n" + 
				"						\"dstrScopCd\": \"0050001\",\r\n" + 
				"						\"dstrScopNm\": \"1차출동\",\r\n" + 
				"						\"dstrStatFl\": \"1\",\r\n" + 
				"						\"dstrStatCd\": \"1\",\r\n" + 
				"						\"dstrStatNm\": \"귀소완료보고\",\r\n" + 
				"						\"ctrlerNm\": \"\",\r\n" + 
				"						\"ctrlerTel\": \"\",\r\n" + 
				"						\"updtDttm\": \"2020-08-14 00:19:00.0\",\r\n" + 
				"						\"dstrNo\": \"UV3600596863\",\r\n" + 
				"						\"dtRegt\": \"2020-08-14 00:07:00.0\"\r\n" + 
				"					}\r\n" + 
				"				]\r\n" + 
				"			},\r\n" + 
				"			\"statuses\": {\r\n" + 
				"				\"status\": [\r\n" + 
				"					{\r\n" + 
				"						\"code\": \"200\",\r\n" + 
				"						\"message\": \"정상 처리\"\r\n" + 
				"					}\r\n" + 
				"				],\r\n" + 
				"				\"rowCount\": 80,\r\n" + 
				"				\"totalCount\": \"80\",\r\n" + 
				"				\"pageNum\": 1,\r\n" + 
				"				\"pageSize\": 100\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"]");
		return sb;
	}
}
