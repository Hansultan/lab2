{
    $("#canvas").bind("click", function(e) {
        let R =  $('#computation-form').find('input[name="R"]').val();
        if (!(isPresented(R, "R", false) && validateParam(R, "R", false))) {
            showWarning("Укажите корректное значение R для использования интерактивного режима графика", "canvas");
            return;
        } else {
            showWarning("", "canvas");
        }

        let canvas = e.target;

        let originalX = e.pageX - canvas.offsetLeft;
        let originalY = e.pageY - canvas.offsetTop;

        let X = toComputingX(originalX, R);
        let Y = toComputingY(originalY, R);

        let form = document.createElement("form");
        form.action = "controller";
        form.method = "GET";

        form.innerHTML = `
                <input name="X" value="${X}">
                <input name="Y" value="${Y}">
                <input name="R" value="${R}">
                <input type="submit">
            `;

        document.body.appendChild(form);
        let hit;
        if (( X <= 0) && (Y >= 0) && (Math.pow(X,2) + Math.pow(Y,2) <= Math.pow(R,2))

            ||( (X >= 0) && (Y <= 0) && (X <= R) && (Y >= -R))

            ||( (X >= 0) && (Y >= 0) && (Y <= R - X) ) )
        {
            hit = true;
        } else {
            hit = false;
        }
        if(hit){
            drawPoint(canvas, originalX, originalY, "GreenYellow");
        }else{
            drawPoint(canvas, originalX, originalY, "Red");
        }
        form.submit();



        // $.ajax({
        //     url: "/area-check",
        //     type: "get",
        //     dataType: "html",
        //     data:
        //         {
        //             "X": String(X).substring(0, 10),
        //             "Y": String(Y).substring(0, 10),
        //             "R": String(R).substring(0, 10)
        //         },
        //     success: function(response) {
        //         let hit = $(response).find(".hit").eq(0).text().trim();
        //         //TODO: добавить возможность сразу отображать результат в result-table(main.jsp)
        //         if (hit === "Попал!") {
        //             drawPoint(canvas, originalX, originalY, "GreenYellow");
        //         } else {
        //             drawPoint(canvas, originalX, originalY, "Red");
        //         }
        // });

    });
}