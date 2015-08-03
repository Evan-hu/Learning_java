package com.ga.click.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class TimeFileRenamePolicy implements FileRenamePolicy {

	public File rename(File f) {
		String name = f.getName();
		String body = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String ext = null;

		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			ext = name.substring(dot);
		} else {
			ext = "";
		}
		int count = 0;
		do {
			String newName = body + count + ext;
			f = new File(f.getParent(), newName);
			count++;
		} while (!createNewFile(f) && count < 9999);
		
		return f;
	}

	private boolean createNewFile(File f) {
		try {
			return f.createNewFile();
		} catch (IOException ignored) {
			return false;
		}
	}
}
