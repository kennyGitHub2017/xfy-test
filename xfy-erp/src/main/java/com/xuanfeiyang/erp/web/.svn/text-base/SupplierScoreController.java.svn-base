package com.xuanfeiyang.erp.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xuanfeiyang.erp.domain.GoodsSupplier;
import com.xuanfeiyang.erp.domain.IoOrderItem;
import com.xuanfeiyang.erp.param.SupplierScoreParam;
import com.xuanfeiyang.erp.service.GoodsSupplierService;
import com.xuanfeiyang.erp.service.IoOrderService;
/**
 * 
 * @author bernard
 *
 */
@Controller
@RequestMapping("/supplier-score")
public class SupplierScoreController extends BaseController{
	
	@Resource
	private IoOrderService ioOrderService; 
	@Resource
	private GoodsSupplierService goodsSupplierService; 
	
	private SupplierScoreParam search = new SupplierScoreParam();

	@RequestMapping("list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("goods/supplier-score");
		mv.addObject("search",search);
		List<GoodsSupplier> allstore = goodsSupplierService.find();
		Iterator<GoodsSupplier> itr = allstore.iterator();
		while (itr.hasNext()){
			if(itr.next().getStatus()!=1){
				itr.remove();
			}
		}
		mv.addObject("supplierList",allstore);
		return mv;
	}

	@RequestMapping(value="score")
	public ModelAndView score(SupplierScoreParam search){
		ModelAndView mv = new ModelAndView();
		//key:sku value:此sku汇总信息所在集合下标
		Map<String,Integer> skuTotalidxMap = new HashMap<String,Integer>();
		//key:sku  value:同个sku记录数
		Map<String,Integer> skuRowspanMap = new HashMap<String,Integer>();
		//key:sku Value:多个sku汇总信息
		Map<String, IoOrderItem> skuTotalMap = new HashMap<String,IoOrderItem>();
		List<IoOrderItem> items =  ioOrderService.supplierScore(search);
		int idx = 0;
		for(IoOrderItem item:items){
			String sku = item.getGoodsSku();
			skuTotalidxMap.put(sku, idx+1);
			if (!skuRowspanMap.containsKey(item.getGoodsSku())){
				skuRowspanMap.put(item.getGoodsSku(), 1);
			}else{
				skuRowspanMap.put(item.getGoodsSku(),skuRowspanMap.get(item.getGoodsSku())+1);
			}
			if (!skuTotalMap.containsKey(item.getGoodsSku())){
				IoOrderItem iim = new IoOrderItem();
				iim.setGoodsSku(item.getGoodsSku());
				iim.setScore(item.getScore());
				skuTotalMap.put(sku, iim);
			}else{
				Float score = skuTotalMap.get(sku).getScore();
				skuTotalMap.get(sku).setScore(score+item.getScore());
			}
			idx ++;
		}
		
		//供应商评分
		IoOrderItem supplyTotal = new IoOrderItem();
		Float allTotal =0f;
		for(Map.Entry<String,IoOrderItem> entry:skuTotalMap.entrySet()){
			IoOrderItem iim = entry.getValue();
			iim.setScore(iim.getScore()/skuRowspanMap.get(entry.getKey()));
			BigDecimal   b   =   new   BigDecimal(iim.getScore());
			float   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
			iim.setScore(f1);
			allTotal += iim.getScore();
		}
		if (skuTotalMap.size()>0){
			BigDecimal   b   =   new   BigDecimal(allTotal/skuTotalMap.size());
			float   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
			supplyTotal.setScore(f1);
		}
		//将多个同sku汇总信息加入集合中
		for(int i=items.size()-1;i>=0;i--){
			String sku = items.get(i).getGoodsSku();
			if (!skuTotalidxMap.containsKey(sku)){
				continue;
			}
			int ix = skuTotalidxMap.get(sku);
			items.add(ix,skuTotalMap.get(sku));
			skuTotalidxMap.remove(sku);
			skuTotalMap.remove(sku);
		}
		
		//加入最后供应商评分
		if (items.size()>0){
			items.add(supplyTotal);
		}
		List<GoodsSupplier> allstore = goodsSupplierService.find();
		Iterator<GoodsSupplier> itr = allstore.iterator();
		while (itr.hasNext()){
			if(itr.next().getStatus()!=1){
				itr.remove();
			}
		}
		if (null!=search && null!=search.getSupplierId()){
			GoodsSupplier supplier = goodsSupplierService.load(search.getSupplierId());
			search.setSupplierName(supplier!=null?supplier.getCompanyName():"");
		}
		mv.addObject("search",search);
		mv.addObject("detailList",items);
		mv.addObject("supplierList",allstore);
		mv.addObject("skuRowspanMap", skuRowspanMap);
		mv.setViewName("goods/supplier-score");
		return mv;
	}
}
