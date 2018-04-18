<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:if test="${!empty successMessage  }">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
		<spring:message code="${successMessage }" text="${successMessage }"  />
	</div>
</c:if>
<c:if test="${!empty infoMessage  }">
	<div class="alert alert-info alert-dismissable">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
		<spring:message code="${infoMessage }" text="${infoMessage }"  />
	</div>
</c:if>
<c:if test="${!empty warningMessage  }">
	<div class="alert alert-warning alert-dismissable">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
		<spring:message code="${warningMessage }" text="${warningMessage }"  />
	</div>
</c:if>
<c:if test="${!empty errorMessage  }">
	<div class="alert alert-danger alert-dismissable">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
		<spring:message code="${errorMessage }" text="${errorMessage }"  />
	</div>
</c:if>