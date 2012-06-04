package pl.looksok.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import pl.looksok.R;
import pl.looksok.customviews.ResultsListAdapter;
import pl.looksok.logic.CcLogic;
import pl.looksok.logic.PeoplePays;
import pl.looksok.utils.Constants;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class CalculationActivity extends ColCalcActivity {
	private CcLogic calc = null;
	private ListView resultList;
	private List<PeoplePays> listArray;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculation);
        
        readInputBundle();
        populateListArray();
    }

	private void populateListArray() {
		listArray = new ArrayList<PeoplePays>();
		
		Set<String> c = calc.getCalculationResult().keySet();
		Iterator<String> it = c.iterator();
		while (it.hasNext()){
			listArray.add(calc.getCalculationResult().get(it.next()));
		}
		
		resultList = (ListView)findViewById(R.id.calc_listView_list);
		ResultsListAdapter adapter = new ResultsListAdapter(getApplicationContext(), R.layout.calculation_list_item, listArray);
		resultList.setAdapter(adapter);
	}

	private void readInputBundle() {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			calc = (CcLogic)extras.getSerializable(Constants.BUNDLE_CALCULATION_OBJECT);
		}
	}

	@Override
	public void onBackPressed() {
    	Intent intent = new Intent(getApplicationContext(), EnterPaysActivity.class) ;
    	intent.putExtra(Constants.BUNDLE_CALCULATION_OBJECT, calc);
    	startActivity(intent);
    	finish();
	}
    
    
}
