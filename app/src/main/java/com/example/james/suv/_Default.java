package com.example.james.suv;

public class _Default {
    public String _mensagem;
    public boolean _status;

    public _Default(){
        this._status = true;
        this._mensagem = "";
    }

    public String get_mensagem() {
        return _mensagem;
    }

    public void set_mensagem(String _mensagem) {
        this._mensagem = _mensagem;
    }

    public boolean is_status() {
        return _status;
    }

    public void set_status(boolean _status) {
        this._status = _status;
    }
}
