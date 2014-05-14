package com.github.typedadapter.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.github.typedadapter.model.Revolution;

import android.app.Activity;

public class RevolutionReader {
    
    @SuppressWarnings("unchecked")
    public List<Revolution> readRevolutions(Activity activity, String filePath) throws IOException {
        ArrayList<Revolution> result = new ArrayList<Revolution>();
        InputStream is = activity.getAssets().open(filePath);
        List<String> lines = IOUtils.readLines(is);
        for (String line : lines) {
            String[] split = line.split(":");
            result.add(new Revolution(split[1], split[0]));
        }
        return result;
    }
}
