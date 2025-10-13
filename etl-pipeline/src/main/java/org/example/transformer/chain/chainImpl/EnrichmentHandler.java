package org.example.transformer.chain.chainImpl;

import lombok.RequiredArgsConstructor;
import org.example.transformer.Enricher.Enrichment;
import org.example.transformer.chain.TransformationHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrichmentHandler extends TransformationHandler {

    final Enrichment enrichment;

    @Override
    public Object process(Object input) {

        return enrichment.enrichData(input);

    }
}
