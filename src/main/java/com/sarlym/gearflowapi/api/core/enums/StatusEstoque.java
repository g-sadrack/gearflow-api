package com.sarlym.gearflowapi.api.core.enums;

public enum StatusEstoque {
    ESGOTADO, // (0 unidades)
    QUASE_ESGOTADO, // (≤ 10 % do mínimo)
    ABAIXO_DO_MÍNIMO, // (até o mínimo)
    NORMAL, // (entre mínimo e máximo)
    ACIMA_DO_IDEAL, // (entre max e 120 % do max)
    EXCESSO // (> 120 % do max)
}
