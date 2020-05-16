package handlers;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;

import console.ConsoleHandler;

public class importHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		
		FileDialog fd=new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),SWT.OPEN);
		  fd.setFilterPath(System.getProperty("JAVA.HOME"));
		  fd.setFilterExtensions(new String[]{"*.xml","*.*"});
		  fd.setFilterNames(new String[]{"XML Files(*.XML)","All Files(*.*)"});
		  String file=fd.open();
		  if(file!=null){
		  File path=new File(file);
		  ConsoleHandler.info("����:"+path.getPath());
		  }
		return null;
	}
	
	
}
