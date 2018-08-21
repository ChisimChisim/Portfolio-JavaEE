package util;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/*
* Page Manager
*
*/

@SessionScoped
public class Pagenation implements Serializable {

	private int max;	//Number of product items	
	private int unit;       //Showing product items per page
	private int pageMax;    //Number of pages
	private int page;       //Current page

	@Inject
	protected transient Logger log;	        //Logger

	public Pagenation() {}

	public void setup(int max, int dpp) {
		this.max		= max;
		this.unit		= dpp;
		this.pageMax	= max/dpp + (max%dpp==0 ? 0 : 1); 
		this.page		= 1;
	}
	public	int firstResult(){
		return	(page-1) * unit ;   //Start from zero
	}
	
	public	int	maxResult(){
		return	unit;
	}
	
	public void next() {
		if(pageMax > page)	++page;
	}
	public void back() {
		if(1 < page)	--page;
	}
	public void bottom() {
		page	= pageMax;
	}
	public void top(){
		page  = 1;
	}

	public int getMax() {
		return max;
	}

	public int getUnit() {
		return unit;
	}

	public int getPageMax() {
		return pageMax;
	}

	public int getPage() {
		return page;
	}
	public	int[] getPos(){
		int[] n = new int[2];
		n[0] = firstResult();	//Start from zero
		n[1] = n[0] + unit - 1;
		return	n;
	}
	@Override
	public String toString() {
		return "Pagenation{" + "max=" + max + ", dpp=" + unit + ", pageMax=" + pageMax + ", page=" + page + '}';
	}

	
}
