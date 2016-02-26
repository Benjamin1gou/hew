function initialize() {
	//ユーザーの現在の位置情報を取得
	navigator.geolocation.getCurrentPosition(successCallback, errorCallback);


	function successCallback(position) {
		//緯度の取得
		var ido = position.coords.latitude;

		//経度の取得
		var keido = position.coords.longitude;



		//取得した座標を使用し、マップの作成をする
		var latlng = new google.maps.LatLng(ido, keido);

		var geocoder = new google.maps.Geocoder();

		geocoder.geocode({
		    latLng: latlng
		  }, function(results, status) {
		    if (status == google.maps.GeocoderStatus.OK) {
		      // results.length > 1 で返ってくる場合もありますが・・・。
		      if (results[6].geometry) {

		          // 住所を取得(日本の場合だけ「日本, 」を削除)
		          var address = results[5].formatted_address.replace(/^日本, /, '');
		          document.getElementById("address").value = address;

		        }
		      }
		     else if (status == google.maps.GeocoderStatus.ERROR) {
		      alert("サーバとの通信時に何らかのエラーが発生！");
		    } else if (status == google.maps.GeocoderStatus.INVALID_REQUEST) {
		      alert("リクエストに問題アリ！geocode()に渡すGeocoderRequestを確認せよ！！");
		    } else if (status == google.maps.GeocoderStatus.OVER_QUERY_LIMIT) {
		      alert("短時間にクエリを送りすぎ！落ち着いて！！");
		    } else if (status == google.maps.GeocoderStatus.REQUEST_DENIED) {
		      alert("このページではジオコーダの利用が許可されていない！・・・なぜ！？");
		    } else if (status == google.maps.GeocoderStatus.UNKNOWN_ERROR) {
		      alert("サーバ側でなんらかのトラブルが発生した模様。再挑戦されたし。");
		    } else if (status == google.maps.GeocoderStatus.ZERO_RESULTS) {
		      alert("見つかりません");
		    } else {
		      alert("えぇ～っと・・、バージョンアップ？");
		    }
		  });

		//表示用
		    document.getElementById("ido").value = ido;
		    document.getElementById("keido").value = keido;

		var opts = {
			zoom: 17,
			center: latlng,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};

		var map = new google.maps.Map(document.getElementById("map_canvas"), opts);

		var latlng = new google.maps.LatLng(ido,keido);
		var marker = new google.maps.Marker({
		  position: latlng,
		  map: map
		});

	}


	function errorCallback(error) {
		  var err_msg = "";
		  switch(error.code)
		  {
		    case 1:
		      err_msg = "位置情報の利用が許可されていません";
		      break;
		    case 2:
		      err_msg = "デバイスの位置が判定できません";
		      break;
		    case 3:
		      err_msg = "タイムアウトしました";
		      break;
		  }
	}
}


