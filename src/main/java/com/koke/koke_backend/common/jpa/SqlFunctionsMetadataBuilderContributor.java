package com.koke.koke_backend.common.jpa;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.StandardSQLFunction;

import static org.hibernate.type.StandardBasicTypes.INTEGER;
import static org.hibernate.type.StandardBasicTypes.STRING;

public class SqlFunctionsMetadataBuilderContributor implements MetadataBuilderContributor {
	@Override
	public void contribute(MetadataBuilder metadataBuilder) {
		metadataBuilder
				.applySqlFunction("JSON_EXTRACT", new StandardSQLFunction("JSON_EXTRACT", STRING))
				.applySqlFunction("JSON_CONTAINS", new StandardSQLFunction("JSON_CONTAINS", INTEGER));
	}
}
