package files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class FileHandler 
{
	public List<String> readFile (String path, String separator)
	{
		List<String> list = new ArrayList<>();
		
		if (StringUtils.isNotEmpty(path) && StringUtils.isNotEmpty(separator))
		{
			String fullPath = getFullPath(path);
			
			try(FileInputStream inputStream = new FileInputStream(fullPath)) 
			{
			    String fileContent = IOUtils.toString(inputStream);
			    
			    if (StringUtils.isNotEmpty(fileContent))
			    {
			    	String[] split = fileContent.split(separator);
			    	
			    	if (ArrayUtils.isNotEmpty(split))
			    		for(String s : split)
			    			if (StringUtils.isNotEmpty(s))
			    				list.add(s.trim());
			    }
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		
		return list;
	}
	
	public void writeFile (String path, String separator, List<String> elements)
	{
		if (StringUtils.isNotEmpty(path) && StringUtils.isNotEmpty(separator) && CollectionUtils.isNotEmpty(elements))
		{
			String fullPath = getFullPath(path);
			
			try 
			{
			    StringBuilder sb = new StringBuilder();
				
				for (String s : elements)
			    	if (StringUtils.isNotEmpty(s))
			    		sb.append(s).append(separator);
				
				if (StringUtils.isNotEmpty(sb.toString()))
				{
					File file = new File(fullPath);
					
					if (!file.exists())
						file.createNewFile();
					
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(sb.toString());
					bw.close();
				}
					
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}
	
	private String getFullPath (String path)
	{
		return new File("").getAbsolutePath() + path;
	}
}