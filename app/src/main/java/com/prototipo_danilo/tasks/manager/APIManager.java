package com.prototipo_danilo.tasks.manager;

import android.content.Context;
import android.os.AsyncTask;

import com.prototipo_danilo.tasks.business.APIBusiness;
import com.prototipo_danilo.tasks.infra.operation.OperationListener;
import com.prototipo_danilo.tasks.infra.operation.OperationResult;

public class APIManager {

    private APIBusiness mapiBusiness;

    public APIManager(Context context){
        this.mapiBusiness = new APIBusiness(context);
    }

    public void Create(final OperationListener listener){
        AsyncTask<Void,Void,OperationResult<Boolean>> task = new AsyncTask<Void, Void, OperationResult<Boolean>>() {
            @Override
            protected OperationResult<Boolean> doInBackground(Void... voids) {
                //implentacao da Businnes
                return mapiBusiness.create();
            }

            @Override
            protected void onPostExecute(OperationResult<Boolean> result){//retorna um objeto anonimo do tipo boolean para finalizar a thread
                int error = result.getError();
                if(error != OperationResult.NO_ERROR){
                    //existe erro
                    listener.OnError(error,result.getErrorMessage());
                }
                else {
                    listener.OnSucess(result.getResult());
                }
            }
        };

        //Executa a thread
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
