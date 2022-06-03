package com.nust.concrete.strength.api.services;

import com.nust.concrete.strength.api.models.dto.ConcreteCompositionInputDto;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.common.io.ClassPathResource;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PredictorService {

    public ResponseEntity<Object> resolveCCSST(ConcreteCompositionInputDto concreteCompositionInputDto) throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {

        String simpleMlp = new ClassPathResource(
                "CCST_predictor.h5").getFile().getPath();
        MultiLayerNetwork model = KerasModelImport.
                importKerasSequentialModelAndWeights(simpleMlp);

        int inputs = 8;

        INDArray features = Nd4j.create(1, inputs);
        features.putScalar( 0, concreteCompositionInputDto.getCement());
        features.putScalar( 1, concreteCompositionInputDto.getBlastFurnaceSlage());
        features.putScalar( 2, concreteCompositionInputDto.getFlyAsh());
        features.putScalar( 3, concreteCompositionInputDto.getWater());
        features.putScalar( 4, concreteCompositionInputDto.getSuperplasticizer());
        features.putScalar( 5, concreteCompositionInputDto.getCoarseAggregator());
        features.putScalar( 6, concreteCompositionInputDto.getFineAggregator());
        features.putScalar( 7, concreteCompositionInputDto.getAge());
        Double prediction = model.output(features).getDouble(0);
        return new ResponseEntity<>( prediction, HttpStatus.OK);
    }

}
