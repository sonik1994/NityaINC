import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* 2.1 -Output : 
 
   Month:Jan Count:  2
   Month:Feb Count:  3
    
*/

public class Question2 {

static void getCountByMonth(String filepath , HashMap<String,Integer> count) 
{
	Stream<Path> path_file=null;
	try {
	path_file = Files.walk(Paths.get(filepath));
	
	List<String> result = path_file.filter(Files::isRegularFile).map(x -> x.toString())
			.collect(Collectors.toList());
	
	for (int i = 0; i < result.size(); i++) {
		
		Path path_files = Paths.get(result.get(i));
				
		BasicFileAttributes br = Files.readAttributes(path_files, BasicFileAttributes.class);
		
		if (!br.isDirectory()) {
			
			String create_date = br.creationTime().toString().substring(0, 10);
			
			Format formatter = new SimpleDateFormat("MMM");
			
			String create_month = formatter.format(new SimpleDateFormat("yyyy-MM-dd").parse(create_date));
			
			if (!count.containsKey(create_month)) 
			{
				count.put(create_month, 1);
			} 
			else 
			{
				count.replace(create_month, (count.get(create_month)) + 1);
			}
		}

	}
	count.forEach((k, v) -> System.out.println("Month: " + k + " Count:" + v));
	
   } 

   catch(NoSuchFileException ex) {
	   System.out.println("Invalid file path . Enter a valid file path .");
   }
   catch(Exception e) {
	   System.out.println("Program failed due to following exception"+e);
   }
	finally {
		if(path_file != null ) 
			path_file.close();
	}
}



	public static void main(String[] args) {

		HashMap<String, Integer> count = new HashMap<String, Integer>();
		System.out.println("Filepath : ");
		Scanner s = new Scanner(System.in);
		String filepath = s.nextLine();
		getCountByMonth(filepath,count);
		s.close();
          
	}
}
