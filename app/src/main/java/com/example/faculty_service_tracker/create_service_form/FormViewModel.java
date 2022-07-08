package com.example.faculty_service_tracker.create_service_form;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FormViewModel extends ViewModel {

    private final MutableLiveData<EventDetailsForm> eventDetailsForm = new MutableLiveData<EventDetailsForm>();
    private final MutableLiveData<OfficeOrderForm> officeOrderForm = new MutableLiveData<OfficeOrderForm>();
    private final MutableLiveData<DRAForm> draForm  = new MutableLiveData<DRAForm>();
    private final MutableLiveData<TerminalReportForm> terminalReportForm = new MutableLiveData<TerminalReportForm>();
    private final MutableLiveData<PhotoBgForm> photoBgForm = new MutableLiveData<PhotoBgForm>();

    public void setData(EventDetailsForm item){
        eventDetailsForm.setValue(item);
    }

    public void setData(OfficeOrderForm obj){
        officeOrderForm.setValue(obj);
    }

    public void setData(DRAForm objDra){
        draForm.setValue(objDra);
    }

    public void setData(TerminalReportForm objTr){
        terminalReportForm.setValue(objTr);
    }

    public void setData(PhotoBgForm objPtb){
        photoBgForm.setValue(objPtb);
    }


    public LiveData<EventDetailsForm> getEventDetailsForm(){
        return eventDetailsForm;

    }

    public LiveData<OfficeOrderForm> getOfficeOder(){

        return officeOrderForm;
    }

    public LiveData<DRAForm> getDra(){

        return draForm;
    }

    public LiveData<TerminalReportForm> getTerminalReport(){
        return terminalReportForm;
    }

    public LiveData<PhotoBgForm> getPhotoBg(){
        return photoBgForm;
    }

}