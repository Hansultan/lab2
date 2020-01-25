//----------------------------------------------------------------------------------------------------------------------
//валидация параметров
const borders = [];
borders["X"] = ["-3", "3"];
borders["Y"] = ["-5", "5"];
borders["R"] = ["1", "4"];

function validate(form) {

    let X = form.X.value.replace("," , ".").trim();
    let Y = form.Y.value.replace("," , ".").trim();
    let R = form.R.value.replace("," , ".").trim();

    form.X.value = X;
    form.Y.value = Y;
    form.R.value = R;

    let valid = true;

    if (!(isPresented(X, "X", true) && validateParam(X, "X", true))) {
        valid = false;
    }
    if (!(isPresented(Y, "Y", true) && validateParam(Y, "Y", true))) {
        valid = false;
    }
    if (!(isPresented(R, "R", true) && validateParam(R, "R", true))) {
        valid = false;
    }
    return valid;

}

function isPresented(param, paramName, warn) {
    if (param == "" || param == null || param.length == 0) {
        if (warn) {
            showWarning(paramName + " должен быть указан", paramName);
        }
        return false;
    } else {
        showWarning("", paramName);
    }


    return true;
}

function validateParam(param, paramName, warn) {
    if (!(!isNaN( Number(param) ) && param.lastIndexOf('.') != (param.length - 1))) {
        if (warn) {
            showWarning(paramName + " должен быть числом", paramName);
        }
        return false;
    }
    let bottomBorder = borders[paramName][0];
    let topBorder = borders[paramName][1];

    if (!(Number(param) > bottomBorder && Number(param) < topBorder)) {
        if (warn) {
            showWarning(paramName + " не входит в необходимый диапазон (" + bottomBorder + " ... " + topBorder +")", paramName);
        }
        return false;
    }
    showWarning("", paramName);
    return true;

}

function showWarning(warningMessage, paramName) {

    let warningContainer = document.getElementById("warning-container-" + paramName);

    if (warningContainer != null) {
        warningContainer.textContent = warningMessage;
    }

}
//----------------------------------------------------------------------------------------------------------------------
//смена темы и текущее время
$(document).ready(function () {
    $("#current-time").html( "Текущее время: " + new Date().toLocaleString() );
    setInterval(() => {
        $("#current-time").html( "Текущее время: " + new Date().toLocaleString());
    }, 1000);

    $("#swapButton").click(function() {
        $("#csslink").attr("href", contextPath + ( lightTheme ? "dark.css" : "light.css" ));
        $(this).val(lightTheme ? "Go White Again" : "Go Black Again");

        lightTheme = !lightTheme;
        document.cookie = "lightTheme=" + lightTheme;
    });
});

//----------------------------------------------------------------------------------------------------------------------
//перерасчет точек на канвасе

const chartWidth = 330;
const chartHeight = 330;
const rCoefficient = 0.4;

function toOriginalX(computingX, R) {
    let X = computingX / R;
    X *= rCoefficient * chartWidth;
    X += chartWidth / 2;

    return X;
}

function toOriginalY(computingY, R) {
    let Y = computingY / R;
    Y *= rCoefficient * chartHeight;
    Y = -Y + chartHeight / 2;

    return Y;
}

function toComputingX(originalX, R) {
    let X = originalX - chartWidth / 2;
    X /= rCoefficient * chartWidth;
    X *= R;

    return X;
}

function toComputingY(originalY, R) {
    let Y = -originalY + chartHeight / 2;
    Y /= rCoefficient * chartHeight;
    Y *= R;

    return Y;
}