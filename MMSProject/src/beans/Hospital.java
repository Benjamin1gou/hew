package beans;

public class Hospital {

	/**
	 * 病院ID
	 */
	private int _hospitalId;

	/**
	 * 病院の緯度
	 */
	private Double _ido;

	/**
	 * 病院の経度
	 */
	private Double _keido;

	/**
	 * 所要時間
	 */
	private int timeRequired;


	/**
	 * setter
	 */
	public void setHospitalId(int hospitalId) {
		this._hospitalId = hospitalId;
	}

	public void setIdo(Double ido) {
		this._ido = ido;
	}

	public void setKeido(Double keido) {
		this._keido = keido;
	}

	public void setTimeRequired(int timeRequired) {
		this.timeRequired = timeRequired;
	}


	/**
	 * getter
	 */
	public int getHospitalId() {
		return _hospitalId;
	}

	public Double getIdo() {
		return _ido;
	}

	public Double getKeido() {
		return _keido;
	}

	public int getTimeRequired() {
		return timeRequired;
	}

}
