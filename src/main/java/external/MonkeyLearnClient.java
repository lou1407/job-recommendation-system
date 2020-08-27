package external;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.monkeylearn.ExtraParam;
import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;


// Model ID: ex_YCya9nrnCreated: 5/2/2018, 11:18:13 AM
// Your API Key: 37bf29057be992f6d2a14f881f4ff3f0172942fe

// Model ID: ex_YCya9nrnCreated: 5/2/2018, 11:18:13 AM
// Your API Key: 8f05595daf2ee648dc4323b96bc8c5ad9f8e6939

// Model ID: ex_YCya9nrnCreated: 5/2/2018, 11:18:13 AM
// Your API Key: 2f75bcf151694481ed6484cc58c7c83eeefb8f8a



public class MonkeyLearnClient {
	private static final String API_KEY = "2f75bcf151694481ed6484cc58c7c83eeefb8f8a";
	private static final String MODEL = "ex_YCya9nrn";
	
	
	public static void main(String[] args) {
		
		String[] textList = {
				"Elon Musk has shared a photo of the spacesuit designed by SpaceX. This is the second image shared of the new design and the first to feature the spacesuit’s full-body look.", };
		List<List<String>> words = extractKeywords(textList);
		for (List<String> ws : words) {
			for (String w : ws) {
				System.out.println(w);
			}
			System.out.println();
		}
	}
	
	public static List<List<String>> extractKeywords(String[] text) {
		if (text == null || text.length == 0) {
			return new ArrayList<>();
		}
		MonkeyLearn ml = new MonkeyLearn(API_KEY);
		ExtraParam[] extraParams = {new ExtraParam("max_keywords", "3")};
		
		MonkeyLearnResponse response;
		try {
			response = ml.extractors.extract(MODEL, text, extraParams);
			JSONArray resultArray = response.arrayResult;
			return getKeywords(resultArray);
		} catch (MonkeyLearnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	/*
	 * JSONArray to List of List
	 * @param mlResultArray
	 * @return
	 */
	private static List<List<String>> getKeywords(JSONArray mlResultArray) {
		List<List<String>> topKeywords = new ArrayList<>();
		for (int i = 0; i < mlResultArray.size(); i++) {
			List<String> keywords = new ArrayList<>();
			JSONArray keywordsArray = (JSONArray) mlResultArray.get(i);
			for (int j = 0; j < keywordsArray.size(); j++) {
				JSONObject keywordObject = (JSONObject) keywordsArray.get(j);
				String keyword = (String)  keywordObject.get("keyword");
				keywords.add(keyword);
					
			}
			topKeywords.add(keywords);
		}
		return topKeywords;
	}
}
