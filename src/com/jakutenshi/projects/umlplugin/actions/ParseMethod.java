package com.jakutenshi.projects.umlplugin.actions;


/**
 * Created by JAkutenshi on 28.05.2016.
 */
public class ParseMethod {
    /*public static Method createMethod(PsiMethod psiMethod) {
        Method method = new Method();
        //разбираем модификаторы
        modifiersAnalyse(method, psiMethod);
        //заполняем имя метода
        method.setName(psiMethod.getName());
        //добавляем входные параметры
        parametersAnalyse(method, psiMethod);
        //возвращаемое значение
        if (psiMethod.getReturnType() != null) {
            method.setReturnType(psiMethod.getReturnType().getPresentableText());
        } else {
            //constructor
            method.setReturnType(null);
        }

        return method;
    }

    private static void modifiersAnalyse(Method method, PsiMethod psiMethod) {
        PsiModifierList psiModifierList = psiMethod.getModifierList();
        PsiElement[] modifiers =  psiModifierList.getChildren();
        for (PsiElement modifier : modifiers) {
            switch (modifier.getText()) {
                case "public" :
                    method.setScope(Scope.PUBLIC);
                    break;
                case "private" :
                    method.setScope(Scope.PRIVATE);
                    break;
                case "package" :
                    method.setScope(Scope.PACKAGE);
                    break;
                case "protected" :
                    method.setScope(Scope.PROTECTED);
                    break;
                case "final" :
                    method.setFinal(true);
                    break;
                case "static" :
                    method.setStatic(true);
                    break;
                default:
                    break;
            }
        }
    }

    private static void parametersAnalyse(Method method, PsiMethod psiMethod) {
        PsiParameterList psiParameterList = psiMethod.getParameterList();
        PsiParameter[] psiParameters = psiParameterList.getParameters();
        ArrayList<Parameter> methodParameters = new ArrayList<>();
        Parameter newMetodParameter;
        for (PsiParameter psiParameter : psiParameters) {
            newMetodParameter = new Parameter(psiParameter.getName(), psiParameter.getType().getPresentableText());
            if (psiParameter.getModifierList().hasModifierProperty("final")) {
                newMetodParameter.setFinal(true);
            }
            methodParameters.add(newMetodParameter);
        }
        method.setParameters(methodParameters);
    }*/
}
