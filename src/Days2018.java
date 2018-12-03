import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Days2018 {

	public String[] convertFileArray(Path path) throws IOException{
		String[] passphrase = Files.lines(path)
				.toArray(String[]::new);
		return passphrase;
	}

	public int[] convertStringArrToInt(String[] cycles){
		
		int[] cyclesInt = new int[cycles.length];
		
		for(int i = 0; i < cycles.length; i++){
			cyclesInt[i] = Integer.parseInt(cycles[i]);
		}
		
		return cyclesInt;
	}
	
	public int countFrequency(int[] frequencies){
		
		int count = 0;
		
		for (int i = 0; i < frequencies.length; i++){
			count = count + frequencies[i];
		}
		
		return count;
		
	}
	
	public List<Character> stringToList(String captcha){
		List<Character> list = new ArrayList<Character>();
		for(char c : captcha.toCharArray()) {
			list.add(c);
		}
		return list;
	}
	
	public int computeChecksum(String[] codeList){
		
		int checksum = 0;
		int sumOf2 = 0;
		int sumOf3 = 0;
		
		HashMap<Integer, Integer> checksumMap = new HashMap<>();
		HashMap<Character, Integer> codeMap = new HashMap<>();
		
		

		
		for (int i = 0; i < codeList.length ; i ++){
			List<Character> tempList = stringToList(codeList[i]);
			
			codeMap.clear();
			checksumMap.clear();
			
			checksumMap.put(2, 0);
			checksumMap.put(3, 0);
			
			tempList.forEach(letter -> {
				if(codeMap.containsKey(letter)){
					codeMap.put(letter, codeMap.get(letter) + 1);
				} else {
					codeMap.put(letter, 1);
				}
			});
			
			codeMap.forEach((key, value) -> {
				if(value == 2 || value == 3){
					checksumMap.put(value, checksumMap.get(value) + 1);
				}
			});
			
			if(checksumMap.get(2) > 0){
				sumOf2++;
			}
			
			if (checksumMap.get(3) > 0){
				sumOf3++;
			}
			
		}
		
		checksum = sumOf2 * sumOf3;
		
		return checksum;
		
	}
	
	public int remFreq(int[] frequencies){
		
		int value = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(value, 0);
		boolean test = true;
		int i = 0;
		
		while(test){
			for (i = 0; i < frequencies.length; i++){
				value = value + frequencies[i];
				
				if(map.containsKey(value)){
					test = false;
					break;
				} else {
					map.put(value, 0);
				}
			}
		}
		return value;
	}
	
	public List<Character> findDiff(String[] ids){
		
		List<Character> match = new ArrayList<Character>();
		int count = 0;
		boolean test = true;
		
		for (int i = 0; i < ids.length && test ; i ++){
			
			List<Character> tempList1 = stringToList(ids[i]);
			
			for (int j = 0; j < ids.length && test ; j ++){
				
				List<Character> tempList2 = stringToList(ids[j]);
				
				if(!tempList1.equals(tempList2)){
					
					for(int k=0; k< tempList1.size(); k++){
						
						if(tempList1.get(k) != tempList2.get(k)){
							count++;
						} else {
							match.add(tempList1.get(k));
						}
					}
					if(count > 1){
						match.clear();
						count = 0;
					} else {
						test = false;
						count = 0;
					}
				}
			}
		}
		return match;
	}
	
	public String getStringRepresentation(List<Character> list){    
	    StringBuilder builder = new StringBuilder(list.size());
	    for(Character ch: list){
	        builder.append(ch);
	    }
	    return builder.toString();
	}

}
