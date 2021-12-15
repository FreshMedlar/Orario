package com.example.orario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import androidx.core.content.ContentProviderCompat.requireContext
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getExcelFile(): File?{
        //Get App Director, APP_DIRECTORY_NAME is a string
        val appDirectory = requireContext().getExternalFilesDir(SyncStateContract.Constants.APP_DIRECTORY_NAME)

        //Check App Directory whether it exists or not
        appDirectory?.let {

            //Check if file exists or not
            if (it.exists()) {

                //return excel file
                return File(appDirectory, SyncStateContract.Constants.FILE_NAME)
            }
        }
        return null
    }

    private fun readExcelAsWorkbook(): Workbook?{

        //Reading excel file
        getExcelFile()?.let {
            try {

                //Reading excel file as stream
                val inputStream = FileInputStream(it)

                //Return workbook
                return WorkbookFactory.create(inputStream)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        return null
    }
}