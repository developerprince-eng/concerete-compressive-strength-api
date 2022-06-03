package com.nust.concrete.strength.api.models.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@ApiModel(value = "Concrete Composition Input")
@Schema(name = "Concrete Composition Input",description = "Input of Concrete Composition")
public class ConcreteCompositionInputDto {

    @ApiModelProperty(notes = "Cement (component 1) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "456")
    @Schema(description = "Cement (component 1) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "456")
    private Float cement;

    @ApiModelProperty(notes = "Blast Furnace Slag (component 2) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "96")
    @Schema(description = "Blast Furnace Slag (component 2) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "96")
    private Float blastFurnaceSlage;

    @ApiModelProperty(notes = "Fly Ash (component 3) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "165")
    @Schema(description = "Fly Ash (component 3) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "165")
    private Float flyAsh;

    @ApiModelProperty(notes = "Water (component 4) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "178")
    @Schema(description = "Water (component 4) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "178")
    private Float water;

    @ApiModelProperty(notes = "Superplasticizer (component 5) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "2.5")
    @Schema(description = "Superplasticizer (component 5) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "2.5")
    private Float superplasticizer;

    @ApiModelProperty(notes = "Coarse Aggregate (component 6) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "1066")
    @Schema(description = "Coarse Aggregate (component 6) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "1066")
    private Float coarseAggregator;

    @ApiModelProperty(notes = "Fine Aggregate (component 7) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "234")
    @Schema(description = "Fine Aggregate (component 7) -- quantitative -- kg in a m3 mixture -- Input Variable", example = "234")
    private Float fineAggregator;

    @ApiModelProperty(notes = "Age -- quantitative -- Day (1~365) -- Input Variable", example = "28")
    @Schema(description = "Age -- quantitative -- Day (1~365) -- Input Variable", example = "28")
    private Float age;
}
