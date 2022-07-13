package com.example.faculty_service_tracker.create_service_form;

import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FormViewModel extends ViewModel {

    private final MutableLiveData<EventDetailsForm> eventDetailsForm = new MutableLiveData<EventDetailsForm>();
    private final MutableLiveData<ImageView> officeOrderForm = new MutableLiveData<ImageView>();
    private final MutableLiveData<ImageView> draForm  = new MutableLiveData<ImageView>();
    private final MutableLiveData<ImageView> terminalReportForm = new MutableLiveData<ImageView>();
    private final MutableLiveData<ImageView> photoBgForm = new MutableLiveData<ImageView>();

    public void setData(EventDetailsForm item){
        eventDetailsForm.setValue(item);
    }

    public void setOfficeOrderImg(ImageView obj){
        officeOrderForm.setValue(obj);
    }

    public void setDraImg(ImageView objDra){
        draForm.setValue(objDra);
    }

    public void setTrImg(ImageView objTr){
        terminalReportForm.setValue(objTr);
    }

    public void setPhotoBg(ImageView objPtb){
        photoBgForm.setValue(objPtb);
    }


    public LiveData<EventDetailsForm> getEventDetailsForm(){
        return eventDetailsForm;

    }

    public LiveData<ImageView> getOfficeOder(){
        return officeOrderForm;
    }

    public LiveData<ImageView> getDra(){
        return draForm;
    }

    public LiveData<ImageView> getTerminalReport(){
        return terminalReportForm;
    }

    public LiveData<ImageView> getPhotoBg(){
        return photoBgForm;
    }

}