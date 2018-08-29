package com.textonphoto.photoeditor.quotecreator.views;

interface BrushViewChangeListener {
    void onViewAdd(BrushDrawingView brushDrawingView);

    void onViewRemoved(BrushDrawingView brushDrawingView);

    void onStartDrawing();

    void onStopDrawing();

}
